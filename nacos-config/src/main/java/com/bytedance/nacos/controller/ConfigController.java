package com.bytedance.nacos.controller;

import com.bytedance.nacos.config.LocalCacheConfig;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: movie
 * @Date: 2021/8/20 19:18
 */
@RestController
@RequestMapping("config")
@RefreshScope
public class ConfigController {
    @Resource
    private LocalCacheConfig localCacheConfig;

    @RequestMapping("/get")
    public boolean get() {
        return localCacheConfig.isEnable();
    }
}
