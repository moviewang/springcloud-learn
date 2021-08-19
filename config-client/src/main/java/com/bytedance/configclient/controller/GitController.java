package com.bytedance.configclient.controller;

import com.bytedance.configclient.model.GitAutoRefreshConfig;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: movie
 * @Date: 2021/8/19 14:50
 */
@RestController
public class GitController {
    @Resource
    private GitAutoRefreshConfig gitAutoRefreshConfig;

    @GetMapping("show")
    public Object show() {
        return gitAutoRefreshConfig;
    }
}
