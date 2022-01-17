package com.wangxb.config;

import com.wangxb.service.DService;
import lombok.SneakyThrows;
import org.springframework.asm.ClassWriter;
import org.springframework.asm.MethodVisitor;
import org.springframework.asm.Opcodes;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;
import java.lang.reflect.Type;

import static org.springframework.asm.Opcodes.*;

@Configuration
public class CustomBeanFactoryAware implements BeanFactoryAware {
    DefaultListableBeanFactory beanFactory;
    @SneakyThrows
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        if (beanFactory instanceof DefaultListableBeanFactory) {
            this.beanFactory = (DefaultListableBeanFactory) beanFactory;
        }
    }
}
