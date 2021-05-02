package com.test.algorithm;

/**
 * @Author: movie
 * @Date: 2020/12/27 23:35
 */
public interface MyMap<K, V> {
    V put(K k, V v);

    V get(K k);


    interface Entry<K, V> {
        K getKey();

        V getValue();
    }

}
