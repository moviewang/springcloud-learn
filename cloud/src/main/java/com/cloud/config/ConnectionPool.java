package com.cloud.config;

/**
 * @Author: movie
 * @Date: 2021/7/23 18:03
 */
public class ConnectionPool extends PoolConfig {
    public ConnectionPool() {
        super();
    }

    public ConnectionPool(Pool pool) {
        super(pool);
    }
}
