package com.test.pattern.decorator;

/**
 * @Author: movie
 * @Date: 2020/4/1 22:10
 */
public class DecoratorTest {
    public static void main(String[] args) {
        No1Component no1 = new No1Component("no1", 1);
        BaseDecorator bd = new No1Decorator(no1);
        bd = new No2Decorator(bd);
        bd.execute();
        System.out.println(bd.getCount());
    }
}
