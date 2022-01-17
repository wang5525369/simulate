package com.wangxb.config;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.util.ClassUtils;

import java.beans.Introspector;

public class CustomNameGenerator implements BeanNameGenerator {
    @Override
    public String generateBeanName(BeanDefinition definition, BeanDefinitionRegistry registry) {
        String beanClassName = definition.getBeanClassName();
        beanClassName = ClassUtils.getShortName(beanClassName);
        if (beanClassName.equals("AService")){
            beanClassName = "TService";
        }
        beanClassName = Introspector.decapitalize(beanClassName);
        return beanClassName;
    }
}
