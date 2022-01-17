package com.wangxb.config;

import lombok.SneakyThrows;
import org.apache.catalina.webresources.TomcatURLStreamHandlerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;
import sun.misc.URLClassPath;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;
import java.util.ArrayList;
import java.util.Hashtable;

public class CustomBeanFilter implements TypeFilter {
    boolean bCreate = false;
    String beanClassName = "com.wangxb.service.EService";

    static {
        //URL.setURLStreamHandlerFactory(new CustomURLStreamHandlerFactory());
        TomcatURLStreamHandlerFactory.getInstance().addUserFactory(new CustomURLStreamHandlerFactory());
        try {
            operPath();
            operLoaders();
        } catch (NoSuchFieldException | IllegalAccessException | MalformedURLException e) {
            e.printStackTrace();
        }
//            Field field = URL.class.getDeclaredField("handlers");
//            field.setAccessible(true);
//            handlers = (Hashtable<String, URLStreamHandler>) field.get(null);
//            handlers.put("file",new CustomURLStreamHandler());


    }

    @SneakyThrows
    @Override
    public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
        if (bCreate == false){

////            Class clazz = null;
////            CustomBean customBean = new CustomBean();
////            byte [] classByte = customBean.createBean(beanClassName);
////            customURLStreamHandler.addMap(beanClassName,classByte);


//            //URL url = new URL("wangxb:" + beanClassName);
//
//

////            //path.add(url);
//////            Method method = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
//////            method.setAccessible(true);
//////            method.invoke(urlClassLoader,new Object[]{url});
//            urlsTemp = urlClassLoader.getURLs();
////            //CustomClassLoad customClassLoad = new CustomClassLoad(urls);
////            //clazz = customClassLoad.createClassForName(beanClassName,classByte);
////            //BService bService = (BService) clazz.newInstance();
////            urlsTemp = urlClassLoader.getURLs();
//////            URL urlTemp = urlClassLoader.getResource(beanClassName);
//////            BService bService = (BService) clazz.newInstance();
////
//////            Object o = clazz.newInstance();
//////            Field field = clazz.getField("i");
//////            field.setAccessible(true);
//////            int i = field.getInt(o);
            bCreate = true;
        }
        AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
        ClassMetadata classMetadata = metadataReader.getClassMetadata();
        Resource resource = metadataReader.getResource();
        String className = classMetadata.getClassName();
        if (className.equals("com.wangxb.service.AService")){
            return false;
        }
        return false;
    }

    static void operPath() throws NoSuchFieldException, IllegalAccessException, MalformedURLException {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        URLClassLoader urlClassLoader = (URLClassLoader) classLoader;
        URL[] urlsTemp = urlClassLoader.getURLs();

        Field field =  URLClassLoader.class.getDeclaredField("ucp");
        field.setAccessible(true);
        URLClassPath urlClassPath = (URLClassPath) field.get(urlClassLoader);
        field = URLClassPath.class.getDeclaredField("path");
        field.setAccessible(true);
        ArrayList<URL> path = (ArrayList<URL>) field.get(urlClassPath);
        int index = 0;
        for(URL urlTemp : path){
            String file = urlTemp.getFile();
            if (file.equals("/F:/workspace/TestDemo/target/classes/")){
                URL replaceUrl = new URL("wangxb", urlTemp.getHost(),urlTemp.getPort(), urlTemp.getFile(),null);
                path.remove(index);
                path.add(index,replaceUrl);
                break;
            }
            index++;
        }
    }

    static void operLoaders() throws NoSuchFieldException, IllegalAccessException {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        URLClassLoader urlClassLoader = (URLClassLoader) classLoader;
        Field field =  URLClassLoader.class.getDeclaredField("ucp");
        field.setAccessible(true);
        URLClassPath urlClassPath = (URLClassPath) field.get(urlClassLoader);
        field = URLClassPath.class.getDeclaredField("loaders");
        field.setAccessible(true);
        ArrayList<Object> loaders = (ArrayList<Object>) field.get(urlClassPath);
        int index = 0;
        for(Object o : loaders){
            Class clasz = o.getClass();
            field = clasz.getSuperclass().getDeclaredField("base");
            field.setAccessible(true);
            URL url = (URL) field.get(o);

            String file = url.getFile();
            if (file.equals("/F:/workspace/TestDemo/target/classes/")){
                field = URL.class.getDeclaredField("protocol");
                field.setAccessible(true);
                field.set(url,"wangxb");
                break;
            }
            index++;
        }
    }
}
