package com.cloud.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @Author: movie
 * @Date: 2021/7/10 14:52
 */
@Service
public class EventService {
    //    @EventListener(classes = {ApplicationEvent.class})
    public void listen() {
        System.out.println("event service received event");
    }

    @Scheduled(cron = "0 */5 * * * ?")
    @Cacheable
    public void test() {
//        int i = 1 / 0;
        System.out.println("cron 1111");
    }
}
