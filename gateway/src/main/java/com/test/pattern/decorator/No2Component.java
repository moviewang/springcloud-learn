package com.test.pattern.decorator;

/**
 * @Author: movie
 * @Date: 2020/4/1 22:16
 */
public class No2Component extends BaseComponent {
    public No2Component(String desc, int count) {
        super(desc, count);
    }

    @Override
    public void execute() {
        System.out.println(desc);
    }

    @Override
    public void addCount() {
        count++;
    }

    @Override
    public int getCount() {
        return count;
    }
}
