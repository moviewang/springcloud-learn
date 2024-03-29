package com.bytedance.nacosconsumer.config;

import feign.Logger;
import feign.Request;
import feign.Response;

import java.io.IOException;

/**
 * @author: movie
 * @date: 2022/1/14 11:32
 */
public class InfoFeignLogger extends Logger {
    private final org.slf4j.Logger logger;

    public InfoFeignLogger(org.slf4j.Logger logger) {
        this.logger = logger;
    }

    @Override
    protected void log(String configKey, String format, Object... args) {
        if (logger.isInfoEnabled()) {
            this.logger.info(String.format(methodTag(configKey) + format, args));
        }
    }

    @Override
    protected void logRequest(String configKey, Level logLevel, Request request) {
        if (logger.isInfoEnabled()) {
            super.logRequest(configKey, logLevel, request);
        }
    }

    @Override
    protected Response logAndRebufferResponse(String configKey, Level logLevel, Response response, long elapsedTime) throws IOException {
        return this.logger.isInfoEnabled() ? super.logAndRebufferResponse(configKey, logLevel, response, elapsedTime) : response;
    }
}
