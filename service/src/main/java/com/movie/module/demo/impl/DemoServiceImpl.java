package com.movie.module.demo.impl;

import common.module.demo.service.DemoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;


/**
 * @Author: movie
 * @Date: 2020/12/11 23:09
 */
@Slf4j
@Service(version = "${service.version}", interfaceClass = DemoService.class)
public class DemoServiceImpl implements DemoService {
    @Override
    public String hello(String name) {
        log.info("hello name[{}]", name);
        return "world";
    }
}
