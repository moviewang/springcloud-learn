package com.test.aop;

import java.lang.reflect.Method;

/**
 * @Author: movie
 * @Date: 2020/3/24 22:20
 */
public class BeforeAdvice implements Advice {
    private Object target;
    private MethodInvocation methodInvocation;

    public BeforeAdvice(Object target, MethodInvocation methodInvocation) {
        this.target = target;
        this.methodInvocation = methodInvocation;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        methodInvocation.invoke();
        return method.invoke(target, args);
    }
}
