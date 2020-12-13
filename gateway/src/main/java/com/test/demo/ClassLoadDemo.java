package com.test.demo;

/**
 * @Author: movie
 * @Date: 2020/3/31 13:14
 */
public class ClassLoadDemo {
    public static void main(String[] args) {
        System.out.println(ClassLoadDemo.class.getClassLoader());
        System.out.println(ClassLoadDemo.class.getClassLoader().getParent());
        System.out.println(ClassLoadDemo.class.getClassLoader().getParent().getParent());
    }
}
