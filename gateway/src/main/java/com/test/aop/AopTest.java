package com.test.aop;

/**
 * @Author: movie
 * @Date: 2020/3/24 22:29
 */
public class AopTest {
    public static void main(String[] args) {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles","true");
        HelloServiceImpl helloService = new HelloServiceImpl();
        MethodInvocation methodInvocation = () -> System.out.println("before");
        Advice advice = new BeforeAdvice(helloService, methodInvocation);
        IHello hello = (IHello) AopProxy.getProxy(helloService, advice);
        System.out.println(hello.getClass());
        hello.hello();
    }
}
