package com.itrus.common.http;

import com.alibaba.fastjson.JSONObject;
import com.itrus.common.config.QunjieFeignConfiguration;
import com.itrus.common.request.qunjie.GetTokenRequestVO;
import com.itrus.common.request.qunjie.SealDeviceRequestVO;
import com.itrus.common.response.qunjie.GetTokenResponseVO;
import com.itrus.common.result.qunjie.QunjieResult;
import com.itrus.common.uag.UagUserApiRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConvertersAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = QunjieApiRequestTest.class)
@Import({FeignAutoConfiguration.class, HttpMessageConvertersAutoConfiguration.class})
@EnableFeignClients(clients = QunjieApiRequestTest.QunjieFeigenClientRequest.class)
class QunjieApiRequestTest {

    @FeignClient(name = "qunjieFeigenClientRequest",url = "http://192.168.100.156:10006",path = "api",configuration = {QunjieFeignConfiguration.class})
    public interface QunjieFeigenClientRequest extends QunjieApiRequest {
    }

    @Autowired
    QunjieFeigenClientRequest qunjieFeigenClientRequest;

    @Test
    void getToken() {
        GetTokenRequestVO getTokenRequestVO = new GetTokenRequestVO();
        getTokenRequestVO.setRestname("mengTest");
        getTokenRequestVO.setPassword("123456");
        QunjieResult<GetTokenResponseVO> qunjieResult = qunjieFeigenClientRequest.getToken(getTokenRequestVO);
        printLog(qunjieResult);

    }

    @Test
    void saveDevice(){
        SealDeviceRequestVO sealDeviceRequestVO = new SealDeviceRequestVO();
        sealDeviceRequestVO.setId("1600000000000000");
        sealDeviceRequestVO.setBlueNo("12345678F152");
        sealDeviceRequestVO.setRfid("3");
        sealDeviceRequestVO.setIsWarning("0");
        sealDeviceRequestVO.setWarningTime("10");
        QunjieResult qunjieResult = qunjieFeigenClientRequest.saveDevice(sealDeviceRequestVO);
        printLog(qunjieResult);
    }

    @Test
    void queryDeviceById(){
        String id = "1600000000000000";
        QunjieResult qunjieResult = qunjieFeigenClientRequest.queryDeviceById(id);
        printLog(qunjieResult);
    }

    @Test
    void updateDevice(){
        SealDeviceRequestVO sealDeviceRequestVO = new SealDeviceRequestVO();
        sealDeviceRequestVO.setId("1600000000000000");
        sealDeviceRequestVO.setBlueNo("12345678F152");
        sealDeviceRequestVO.setRfid("3");
        sealDeviceRequestVO.setIsWarning("1");
        sealDeviceRequestVO.setWarningTime("10");
        QunjieResult qunjieResult = qunjieFeigenClientRequest.updateDevice(sealDeviceRequestVO);
        printLog(qunjieResult);
    }

    void printLog(QunjieResult qunjieResult){
        String methodName = getRunMethodName(3);
        if (qunjieResult.isNotOk()){
            log.error("调用失败({})，返回:{}" ,methodName, JSONObject.toJSONString(qunjieResult));
        }else{
            log.info("调用成功({})，返回:{}" ,methodName, JSONObject.toJSONString(qunjieResult));
        }
    }

    String getRunMethodName(int index){
        String methodName = "";
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        if (stackTraceElements != null && stackTraceElements.length >= (index+1)){
            methodName = stackTraceElements[index].getMethodName();
        }
        return methodName;
    }
}