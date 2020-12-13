package com.test.config;

import org.springframework.stereotype.Component;

/**
 * @Author: movie
 * @Date: 2020/6/4 14:58
 */
@Component
public class TestFb {
    private int a;

    public void doSomething() {
        System.out.println("do something");
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }
}
