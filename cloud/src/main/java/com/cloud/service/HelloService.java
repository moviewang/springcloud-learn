package com.cloud.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.concurrent.TimeUnit;

/**
 * @Author: movie
 * @Date: 2021/7/8 14:04
 */
@Service
public class HelloService {
    @Resource
    private RestTemplate restTemplate;

    @SentinelResource(value = "user", blockHandler = "helloBlock", fallback = "helloFallback")
    public String hello(String name) {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int i = 1 / 0;
        return "hello!" + name;
    }

    public String helloBlock(String name, BlockException e) {
        return "hello!" + "ooops";
    }

    public String helloFallback(String name) {
        return "hello!" + "fallback";
    }

    public String reqBaidu() {
        ResponseEntity<String> resp = restTemplate.getForEntity(URI.create("http://www.baidu.com"), String.class);
        return resp.getBody();
    }

}

