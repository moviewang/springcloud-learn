package com.cloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.cloud.common.ResponseResultBody;
import com.cloud.common.Result;
import com.cloud.config.ConnectionPool;
import com.cloud.config.PoolConfig;
import com.cloud.module.employee.service.EmployeeService;
import com.cloud.service.HelloService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: movie
 * @Date: 2021/7/8 14:27
 */
@Slf4j
@RestController
@RequestMapping("hello")
public class HelloController {
    @Resource
    private HelloService helloService;
    @Resource
    private PoolConfig poolConfig;
    @Resource
    private ConnectionPool connectionPool;
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private EmployeeService employeeService;

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
    @ResponseResultBody
    public Result reqBaidu() {
//        String res = stringRedisTemplate.opsForValue().get("test");
        employeeService.zadd();
        log.info("poolconfig：{}", poolConfig.getMaxActive());
        log.info("connnection pool：{}", connectionPool.getMaxActive());
        return Result.success();
    }
}

