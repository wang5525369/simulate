package com.wangxb.config;

import com.wangxb.service.AService;
import com.wangxb.service.DService;
import lombok.SneakyThrows;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @SneakyThrows
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        /*
        String className = DService.class.getSimpleName();
        if (beanName.indexOf(className)>-1) {
            CustomBean customBean = new CustomBean();
            Class clasz = customBean.createBean();
            bean = clasz.newInstance();
        }
        */
        return bean;
    }
}
