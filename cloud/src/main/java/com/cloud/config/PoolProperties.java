package com.cloud.config;

/**
 * @Author: movie
 * @Date: 2021/8/10 18:22
 */
public class PoolProperties implements Pool {
    private int maxActive;

    @Override
    public int getMaxActive() {
        return maxActive;
    }

    @Override
    public void setMaxActive(int maxActive) {
        this.maxActive = maxActive;
    }
}
