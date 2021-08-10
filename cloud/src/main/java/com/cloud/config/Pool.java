package com.cloud.config;

/**
 * @Author: movie
 * @Date: 2021/7/23 17:21
 */
public interface Pool {
    int getMaxActive();

    void setMaxActive(int maxActive);
}
