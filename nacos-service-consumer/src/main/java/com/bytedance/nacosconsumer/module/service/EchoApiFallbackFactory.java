package com.bytedance.nacosconsumer.module.service;

import com.bytedance.nacosconsumer.NacosConsumerApplication;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Service;

/**
 * @author: movie
 * @date: 2022/1/18 12:18
 */
@Service
public class EchoApiFallbackFactory implements FallbackFactory<NacosConsumerApplication.EchoApi> {
    @Override
    public NacosConsumerApplication.EchoApi create(Throwable cause) {
        return string -> {
            if (cause instanceof RuntimeException) {
                return "sentinel";
            }
            return string;
        };
    }
}
