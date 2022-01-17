package com.wangxb.config;

import sun.net.www.protocol.file.Handler;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CustomURLStreamHandler extends URLStreamHandler {

    String customUrlProtocol = "wangxb";
    Map<String, byte[]> map = new ConcurrentHashMap<>();

    public void addMap(String className,byte[] data){
        map.put(className,data);
    }

    @Override
    protected URLConnection openConnection(URL u) throws IOException {
        return new CustomURLConnection(u);
    }
}
