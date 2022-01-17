package com.wangxb.config;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.net.URLStreamHandlerFactory;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CustomURLStreamHandlerFactory implements URLStreamHandlerFactory {

    @Override
    public URLStreamHandler createURLStreamHandler(String protocol) {
        if (protocol.equals("wangxb")) {
            return new CustomURLStreamHandler();
        }
        return null;
    }


}
