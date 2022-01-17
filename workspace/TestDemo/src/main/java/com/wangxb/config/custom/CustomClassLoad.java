package com.wangxb.config;

import java.net.URL;
import java.net.URLClassLoader;

public class CustomClassLoad extends URLClassLoader {


    public CustomClassLoad(URL[] urls){
        super(urls);
    }
//
//    public Class createClassForName(String className,byte[] classData){
//        return super.defineClass(className,classData,0,classData.length);
//    }
//
    @Override
    protected Class findClass(String name) throws ClassNotFoundException {
        return super.findClass(name);
    }
//
//    @Override
//    public Class<?> loadClass(String name) throws ClassNotFoundException {
//        return super.loadClass(name);
//    }
//
//    @Override
//    public URL getResource(String name) {
//        return super.getResource(name);
//    }

}
