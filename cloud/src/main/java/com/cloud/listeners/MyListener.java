package com.cloud.listeners;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * @Author: movie
 * @Date: 2021/7/10 14:51
 */
//@Component
public class MyListener implements ApplicationListener<ApplicationEvent> {
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        System.out.println("myListener received event");
    }
}
