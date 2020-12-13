package com.test.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author: movie
 * @Date: 2020/4/1 15:09
 */
public class ThreadPoolExecutorTest {
    public static void main(String[] args) {
        int coreSize = 1;
        int maxPoolSize = 2;
        ArrayBlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(1);
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(coreSize, maxPoolSize, 0, TimeUnit.SECONDS, queue);
        poolExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());
        for (int i = 0; i < 10; i++) {
            final int index = i;
            poolExecutor.execute(() -> {
                System.out.println(Thread.currentThread().getName() + "begin run task :" + index);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "finish run task :" + index);
            });
        }
        System.out.println("main thread before sleep");
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("before shutdown");
        poolExecutor.shutdown();
        System.out.println("after shutdown isTermined" + poolExecutor.isTerminated());
        try {
            poolExecutor.awaitTermination(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("final shutdown isTermined" + poolExecutor.isTerminated());

    }

}