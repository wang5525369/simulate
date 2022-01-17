package com.wangxb.feign;

import feign.FeignException;
import feign.Response;
import feign.Util;
import feign.codec.DecodeException;
import feign.codec.Decoder;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.lang.reflect.Type;

import static java.lang.String.format;

@Slf4j
public class CustomStringDecoder implements Decoder {

    @Override
    public Object decode(Response response, Type type) throws IOException, DecodeException, FeignException {
        type = String.class;
        Response.Body body = response.body();
        if (body == null) {
            return null;
        }
        if (String.class.equals(type)) {
            return Util.toString(body.asReader(Util.UTF_8));
        }
        throw new DecodeException(response.status(),
                format("%s is not a type supported by this decoder.", type), response.request());
    }
}
