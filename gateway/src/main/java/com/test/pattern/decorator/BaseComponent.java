package com.test.pattern.decorator;

/**
 * @Author: movie
 * @Date: 2020/4/1 21:54
 */
public abstract class BaseComponent implements BaseComponentFacade {
    protected String desc;
    protected int count;

    public BaseComponent(String desc, int count) {
        this.desc = desc;
        this.count = count;
    }
}
