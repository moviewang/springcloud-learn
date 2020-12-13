package com.test.rpc;

import org.springframework.beans.factory.FactoryBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @Author: movie
 * @Date: 2020/3/27 17:26
 */
public class ServiceFactory<T> implements FactoryBean<T> {
    private Class<T> interfaceType;

    public ServiceFactory(Class<T> interfaceType) {
        this.interfaceType = interfaceType;
    }

    @Override
    public T getObject() throws Exception {
        InvocationHandler invocationHandler = new ServiceProxy<>(interfaceType);
        return (T) Proxy.newProxyInstance(interfaceType.getClassLoader(), new Class[]{interfaceType}, invocationHandler);
    }

    @Override
    public Class<?> getObjectType() {
        return interfaceType;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
