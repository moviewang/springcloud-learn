package com.test;

import com.test.config.TestFb;
import com.test.es.EsIndexService;
import com.test.es.UserService;
import common.module.demo.service.DemoService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @Author: movie
 * @Date: 2020/3/27 13:56
 */
@SpringBootApplication
@EnableConfigurationProperties
public class Application {
    @Resource
    private TestFb testFb;
    @DubboReference(version = "1.0.0")
    private DemoService demoService;
    @Resource
    private EsIndexService esIndexService;
    @Resource
    private UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @PostConstruct
    public void init() {
        testFb.doSomething();
        System.out.println(testFb.getA());
        System.out.println(demoService.hello("world"));
        userService.addDoc();
    }
}
