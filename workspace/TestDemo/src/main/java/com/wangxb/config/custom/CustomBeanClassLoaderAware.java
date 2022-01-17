package com.wangxb.config;

import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.stereotype.Component;
import sun.applet.AppletClassLoader;

@Component
public class CustomBeanClassLoaderAware implements BeanClassLoaderAware {

    ClassLoader classLoader = null;

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }
}
