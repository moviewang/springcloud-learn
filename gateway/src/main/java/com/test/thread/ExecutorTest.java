package com.test.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: movie
 * @Date: 2021/5/5 17:20
 */
public class ExecutorTest {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(10);
    }
}
