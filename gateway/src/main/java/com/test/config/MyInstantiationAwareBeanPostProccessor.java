package com.test.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @Author: movie
 * @Date: 2020/6/4 15:06
 */
//@Configuration
public class MyInstantiationAwareBeanPostProccessor implements InstantiationAwareBeanPostProcessor, ApplicationContextAware {
    private ApplicationContext applicationContext;

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        System.out.println(beanName + " begin before instantiation");
//        if (beanClass == TestFb.class) {
//            Enhancer enhancer = new Enhancer();
//            enhancer.setSuperclass(beanClass);
//            enhancer.setCallback(new MyMethodIntercept());
//            TestFb testFb = (TestFb) enhancer.create();
//            System.out.println("return dynamic proxy");
//            return testFb;
//        }
        return null;
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        System.out.println(beanName + " post after instantiation");
        return true;
    }

    @Override
    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        System.out.println(beanName + " postProcessProperties");
        if (bean instanceof TestFb) {
            System.out.println("old value" + ((TestFb) bean).getA());
            ((TestFb) bean).setA(100);
        }
        return pvs;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println(beanName + " postProcessBeforeInitialization");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println(beanName + " postProcessAfterInitialization");
        return bean;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
