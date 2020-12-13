package com.test;

import com.test.config.TestFb;
import common.module.demo.service.DemoService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @Author: movie
 * @Date: 2020/3/27 13:56
 */
@SpringBootApplication
public class Application {
    @Resource
    private TestFb testFb;
    @DubboReference(version = "1.0.0")
    private DemoService demoService;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @PostConstruct
    public void init() {
        testFb.doSomething();
        System.out.println(testFb.getA());
        System.out.println(demoService.hello("world"));
    }
}
