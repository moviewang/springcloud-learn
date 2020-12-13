package com.test.pattern.decorator;

/**
 * @Author: movie
 * @Date: 2020/4/1 22:08
 */
public class No2Decorator extends BaseDecorator {
    public No2Decorator(BaseComponentFacade baseComponentFacade) {
        super(baseComponentFacade);
    }

    @Override
    public void execute() {
        addCount();
        super.execute();
        System.out.println("no2");
    }
}

