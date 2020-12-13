package com.test.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @Author: movie
 * @Date: 2020/3/24 22:25
 */
public class AopProxy {
    public static Object getProxy(Object target, InvocationHandler invocationHandler) {
        return Proxy.newProxyInstance(AopProxy.class.getClassLoader(), target.getClass().getInterfaces(), invocationHandler);
    }
}
