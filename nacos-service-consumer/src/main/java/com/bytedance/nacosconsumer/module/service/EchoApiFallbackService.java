package com.bytedance.nacosconsumer.module.service;

import com.bytedance.nacosconsumer.NacosConsumerApplication;
import org.springframework.stereotype.Service;

/**
 * @author: movie
 * @date: 2022/1/17 19:27
 */
@Service
public class EchoApiFallbackService implements NacosConsumerApplication.EchoApi {
    @Override
    public String echo(String string) {
        return "hystrix";
    }
}
