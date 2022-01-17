package com.itrus.common.qunjie;

import com.itrus.common.http.QunjieRequest;
import com.itrus.common.http.UagRequest;
import com.itrus.common.request.qunjie.GetTokenRequestVO;
import com.itrus.common.response.qunjie.GetTokenResponseVO;
import com.itrus.common.result.qunjie.QunjieResult;
import com.itrus.uc.common.bean.open.req.company.GetCompanyReq;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class QunjieScheduled  implements CommandLineRunner {

    @Value("${qunjie.user}")
    String user;
    @Value("${qunjie.pwd}")
    String pwd;
    @Autowired
    QunjieRequest qunjieRequest;
    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @Autowired
    UagRequest uagRequest;

    String redisTokenName = "qunjie:token:value";
    String redisTokenSet = "qunjie:token:set";
    long tokenTimeOut = 60*60*2;

    @Scheduled(cron = "0 0 0/1 * * ? ")  //每隔一小时执行一次
    String setToken(){
        String token = "";
        int set = 0;
        Integer ret = (Integer) redisTemplate.opsForValue().get(redisTokenSet);
        if (ret != null){
            set = ret.intValue();
        }
        set++;
        ret = Math.toIntExact(redisTemplate.opsForValue().increment(redisTokenSet));
        if (ret.intValue() == set) {  //只能一台机器执行，去除判断多台执行覆盖也可以
            token = getQunjieToken();
            if (StringUtils.isNotEmpty(token)) {
                setRedisToken(token);
            }
        }
        return token;
    }

    String getRedisToken(){
        String token = (String) redisTemplate.opsForValue().get(redisTokenName);
        if (StringUtils.isEmpty(token)){
            token = setToken();
        }
        return token;
    }

    String setRedisToken(String token){
        redisTemplate.opsForValue().set(redisTokenName,token,tokenTimeOut, TimeUnit.SECONDS);
        return token;
    }

    String getQunjieToken(){
        String token = "";
        GetTokenRequestVO getTokenRequestVO = new GetTokenRequestVO();
        getTokenRequestVO.setRestname(user);
        getTokenRequestVO.setPassword(pwd);
        QunjieResult<GetTokenResponseVO> qunjieResult = qunjieRequest.getToken(getTokenRequestVO);
        if (qunjieResult.isOk()) {
            GetTokenResponseVO getTokenResponseVO = qunjieResult.getData();
            if (ObjectUtils.isNotEmpty(getTokenResponseVO)) {
                token = getTokenResponseVO.getToken();
            }
        }
        return token;
    }

    @Override
    public void run(String... args) throws Exception {
        setToken();

    }
}
