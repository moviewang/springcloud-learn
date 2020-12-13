package com.test.pattern.decorator;

/**
 * @Author: movie
 * @Date: 2020/4/1 22:02
 */
public abstract class BaseDecorator implements BaseComponentFacade {
    protected BaseComponentFacade baseComponentFacade;

    public BaseDecorator(BaseComponentFacade baseComponentFacade) {
        this.baseComponentFacade = baseComponentFacade;
    }

    @Override
    public void execute() {
        baseComponentFacade.execute();
    }

    @Override
    public void addCount() {
        baseComponentFacade.addCount();
    }

    @Override
    public int getCount() {
        return baseComponentFacade.getCount();
    }
}
