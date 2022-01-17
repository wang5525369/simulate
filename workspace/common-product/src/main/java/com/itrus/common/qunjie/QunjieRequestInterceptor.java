package com.itrus.common.qunjie;

import com.itrus.common.http.QunjieRequest;
import com.itrus.common.request.qunjie.GetTokenRequestVO;
import com.itrus.common.response.qunjie.GetTokenResponseVO;
import com.itrus.common.result.qunjie.QunjieResult;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;

public class QunjieRequestInterceptor implements RequestInterceptor {

    String user;
    String pwd;
    QunjieRequest qunjieRequest;
    RedisTemplate<String, Object> redisTemplate;
    String redisTokenName = "qunjie:token:value";
    String redisTokenSet = "qunjie:token:set";
    long tokenTimeOut = 60*60*2;

    public QunjieRequestInterceptor(String user, String pwd, QunjieRequest qunjieRequest,@Autowired RedisTemplate<String, Object> redisTemplate){
        this.user = user;
        this.pwd = pwd;
        this.qunjieRequest = qunjieRequest;
        this.redisTemplate = redisTemplate;
        getRedisToken();
    }

    @Override
    public void apply(RequestTemplate template) {
        if (template.url().startsWith("/token/") == false) {
            String token = getRedisToken();
            template.header("Authorization", "Bearer " + token);
        }
    }

    @Scheduled(cron = "* * 0/1  * * ? ")  //每隔一小时执行一次
    String setToken(){
        String token = "";
        long set = (long) redisTemplate.opsForValue().get(redisTokenSet);
        set++;
        long ret = redisTemplate.opsForValue().increment(redisTokenSet);
        if (ret == set) {  //只能一台机器执行，多台执行覆盖也可以
            token = getQunjieToken();
            setRedisToken(token);
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
        redisTemplate.opsForValue().set(redisTokenName,token,tokenTimeOut);
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

}
