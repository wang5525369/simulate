package com.itrus.common.uag;

import feign.InvocationHandlerFactory;
import feign.ReflectiveFeign;
import feign.Target;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;

public class CustomInvocationHandlerFactory implements InvocationHandlerFactory {
    @Override
    public InvocationHandler create(Target target, Map<Method, MethodHandler> dispatch) {
        return new CustomInvocationHandler(target, dispatch);
    }
}
