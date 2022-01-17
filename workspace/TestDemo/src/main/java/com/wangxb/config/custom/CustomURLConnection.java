package com.wangxb.config;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownServiceException;

public class CustomURLConnection extends URLConnection {
    URL url;

    public CustomURLConnection(URL url) {
        super(url);
        this.url = url;
    }

    @Override
    public void connect() throws IOException {
        URLConnection urlConn = url.openConnection();
    }

    @Override
    public InputStream getInputStream() throws IOException {
        throw new UnknownServiceException("protocol doesn't support input");
    }
}
