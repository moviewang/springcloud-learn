package com.cloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.cloud.service.HelloService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: movie
 * @Date: 2021/7/8 14:27
 */
@RestController
@RequestMapping("hello")
public class HelloController {
    @Resource
    private HelloService helloService;

    @GetMapping
    public String hello() {
        return helloService.hello("jack");
    }

    @GetMapping("test")
    @SentinelResource(value = "hello", blockHandler = "test")
    public String hello1() {
        return helloService.hello("test multi resources");
    }

    public String test(BlockException e) {
        return "test";
    }

    @GetMapping("baidu")
    public String reqBaidu() {
        return helloService.reqBaidu();
    }

}
