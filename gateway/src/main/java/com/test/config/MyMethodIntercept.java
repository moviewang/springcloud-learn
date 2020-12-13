package com.test.config;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Author: movie
 * @Date: 2020/6/4 15:00
 */
public class MyMethodIntercept implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("target method before");
        Object object = methodProxy.invokeSuper(o, objects);
        System.out.println("target method after");
        return object;
    }
}
