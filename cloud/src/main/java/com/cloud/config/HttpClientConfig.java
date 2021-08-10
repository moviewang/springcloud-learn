package com.cloud.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: movie
 * @Date: 2021/7/14 15:20
 */
@Configuration
public class HttpClientConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplateBuilder().build();
    }

    @Bean
    @ConfigurationProperties(prefix = "pool.pp")
    public ConnectionPool connectionPool() {
        return new ConnectionPool();
    }
}
