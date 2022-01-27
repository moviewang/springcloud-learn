package com.bytedance.nacos.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

/**
 * @author: movie
 * @date: 2022/1/27 14:48
 */
@Getter
@Setter
@Configuration(proxyBeanMethods = false)
@ConfigurationProperties(prefix = "use-local-cache")
@RefreshScope
public class LocalCacheConfig {
    private boolean enable;
}
