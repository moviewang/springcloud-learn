package com.test.aop;

/**
 * @Author: movie
 * @Date: 2020/3/24 22:18
 */
public class HelloServiceImpl implements IHello {
    @Override
    public void hello() {
        System.out.println("hello");
    }
}
