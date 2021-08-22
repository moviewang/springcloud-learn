package com.bytedance.nacosconsumer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @Author: movie
 * @Date: 2021/8/22 17:19
 */
@SpringBootApplication
public class NacosConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(NacosConsumerApplication.class, args);
    }

    @Resource
    private LoadBalancerClient loadBalancerClient;
    @Resource
    private RestTemplate restTemplate;
    @Value("${spring.application.name}")
    private String appName;

    @RestController
    public class Test {
        @GetMapping("/echo/app-name")
        public String echoAppName() {
            //使用 LoadBalanceClient 和 RestTemolate 结合的方式来访问
            ServiceInstance serviceInstance = loadBalancerClient.choose("nacos-provider");
            String url = String.format("http://%s:%s/echo/%s", serviceInstance.getHost(), serviceInstance.getPort(), appName);
            System.out.println("request url:" + url);
            return restTemplate.getForObject(url, String.class);
        }

        @GetMapping("echo")
        public String echo() {
            return restTemplate.getForObject("http://nacos-provider/echo/nacos-consumer", String.class);
        }
    }

    @Bean
//    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplateBuilder().build();
    }
}
