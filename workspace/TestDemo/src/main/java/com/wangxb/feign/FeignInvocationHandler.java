package com.wangxb.feign;

import feign.InvocationHandlerFactory;
import feign.Target;
import org.springframework.util.ReflectionUtils;
import sun.reflect.ReflectionFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;

public class CustomInvocationHandler implements InvocationHandler {

    private final Target target;
    private final Map<Method, InvocationHandlerFactory.MethodHandler> dispatch;
    Method oldMethd = null;
    InvocationHandlerFactory.MethodHandler oldMethodHandler;
    public CustomInvocationHandler(Target target,Map<Method, InvocationHandlerFactory.MethodHandler> dispatch){
        this.target = target;
        this.dispatch = dispatch;
        for (Method method : dispatch.keySet()){
            String name = method.getName();
            if (name.equals("listByUuid")){
                oldMethd = method;
                oldMethodHandler = dispatch.get(method);
                dispatch.remove(method);
                Field field = ReflectionUtils.findField(method.getClass(),"slot");
                ReflectionUtils.makeAccessible(field);
                int slot = (int) ReflectionUtils.getField(field,method);
                field = ReflectionUtils.findField(method.getClass(),"signature");
                ReflectionUtils.makeAccessible(field);
                String signature = (String) ReflectionUtils.getField(field,method);
                //Ljava/lang/String;
                String [] arraySignature = signature.split("\\)");
                signature = arraySignature[0] + ")" + "Ljava/lang/String;";

                field = ReflectionUtils.findField(method.getClass(),"annotations");
                ReflectionUtils.makeAccessible(field);
                byte[] annotations = (byte[]) ReflectionUtils.getField(field,method);
                field = ReflectionUtils.findField(method.getClass(),"annotationDefault");
                ReflectionUtils.makeAccessible(field);
                byte[] annotationDefault = (byte[]) ReflectionUtils.getField(field,method);
                field = ReflectionUtils.findField(method.getClass(),"parameterAnnotations");
                ReflectionUtils.makeAccessible(field);
                byte[]  parameterAnnotations = (byte[]) ReflectionUtils.getField(field,method);;
                method = ReflectionFactory.getReflectionFactory().newMethod(method.getDeclaringClass(), method.getName(), method.getParameterTypes(), String.class, method.getExceptionTypes(), method.getModifiers(), slot, signature, annotations, parameterAnnotations,annotationDefault);
                dispatch.put(method,oldMethodHandler);
                break;
            }
        }
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if ("equals".equals(method.getName())) {
            try {
                Object otherHandler =
                        args.length > 0 && args[0] != null ? Proxy.getInvocationHandler(args[0]) : null;
                return equals(otherHandler);
            } catch (IllegalArgumentException e) {
                return false;
            }
        } else if ("hashCode".equals(method.getName())) {
            return hashCode();
        } else if ("toString".equals(method.getName())) {
            return toString();
        }
        Object o = null;
        InvocationHandlerFactory.MethodHandler methodHandler = dispatch.get(method);
        if (method.getName().equals("listByUuid")){
            o = oldMethodHandler.invoke(args);
        }else {
            o = methodHandler.invoke(args);
        }
        return o;
    }
}
