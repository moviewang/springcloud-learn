package com.test.pattern.decorator;

/**
 * @Author: movie
 * @Date: 2020/4/1 22:07
 */
public class No1Decorator extends BaseDecorator {
    public No1Decorator(BaseComponentFacade baseComponentFacade) {
        super(baseComponentFacade);
    }

    @Override
    public void execute() {
        addCount();
        super.execute();
        System.out.println("no1");
    }
}
