package com.test.pattern.singleton;

/**
 * @Author: movie
 * @Date: 2020/4/7 15:27
 */
public class SingletonTest {
    private SingletonTest() {
    }

    private static class SingletonHolder {
        private static final SingletonTest SINGLETON_TEST = new SingletonTest();
    }

    public static SingletonTest getInstance() {
        return SingletonHolder.SINGLETON_TEST;

    }
}
