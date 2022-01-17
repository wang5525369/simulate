package com.itrus.common.config;

import com.itrus.common.http.QunjieRequest;
import com.itrus.common.qunjie.QunjieRequestInterceptor;
import feign.Logger;
import feign.RequestInterceptor;
import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;

public class QunjieFeignConfiguration {
    @Bean
    Logger.Level deviceSendCmdFeignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    Encoder feignFormEncoder(ObjectFactory<HttpMessageConverters> converters) {
        return new SpringFormEncoder(new SpringEncoder(converters));
    }

    @Bean
    RequestInterceptor requestInterceptor(@Value("${qunjie.user}")String user, @Value("${qunjie.pwd}")String pwd, @Qualifier("qunjieRequest")QunjieRequest qunjieRequest,@Autowired RedisTemplate<String, Object> redisTemplate){
        return new QunjieRequestInterceptor(user,pwd,qunjieRequest,redisTemplate);
    }
}
