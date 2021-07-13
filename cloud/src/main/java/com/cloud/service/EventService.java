package com.cloud.service;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

/**
 * @Author: movie
 * @Date: 2021/7/10 14:52
 */
@Service
public class EventService {
    @EventListener(classes = {ApplicationEvent.class})
    public void listen() {
        System.out.println("event service received event");
    }
}
