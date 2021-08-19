package com.bytedance.configclient.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: movie
 * @Date: 2021/8/19 14:47
 */
@Data
@Configuration(proxyBeanMethods = false)
@ConfigurationProperties(prefix = "info")
public class GitAutoRefreshConfig {
    private String profile;
    private String from;
}

