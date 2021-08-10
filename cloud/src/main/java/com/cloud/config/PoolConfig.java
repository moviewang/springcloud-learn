package com.cloud.config;

/**
 * @Author: movie
 * @Date: 2021/7/22 18:02
 */
public class PoolConfig implements Pool {
    private Pool pool;

    public PoolConfig() {
        this(new PoolProperties());
        System.out.println("empty construct");
    }

    public PoolConfig(Pool pool) {
        System.out.println("pool construct");
        this.pool = pool;
    }

    @Override
    public int getMaxActive() {
        return pool.getMaxActive();
    }

    @Override
    public void setMaxActive(int maxActive) {
        pool.setMaxActive(maxActive);
    }
}
