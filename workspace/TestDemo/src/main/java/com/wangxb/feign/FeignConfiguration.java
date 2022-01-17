package com.wangxb.feign;

import feign.Feign;
import feign.InvocationHandlerFactory;
import feign.codec.Decoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

public class CustomConfiguration {

    @Bean
    InvocationHandlerFactory invocationHandlerFactory(){
        return new CustomInvocationHandlerFactory();
    }

    @Bean
    public Decoder customStringDecoder() {
        //return new StringDecoder();
        return new CustomStringDecoder();
    }
    @Bean
    @Scope("prototype")
    public Feign.Builder feignBuilder() {
        return Feign.builder().invocationHandlerFactory(new CustomInvocationHandlerFactory());

    }
}
