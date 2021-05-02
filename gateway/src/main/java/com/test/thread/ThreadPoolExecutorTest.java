package com.test.thread;

import java.util.concurrent.*;

/**
 * @Author: movie
 * @Date: 2020/4/1 15:09
 */
public class ThreadPoolExecutorTest {
    private static volatile boolean flag = false;
    private static volatile int cnt = 0;

    public static void main(String[] args) throws InterruptedException {
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
        Thread running = new Thread(() -> {
            while (!flag) {
                System.out.println("running");
            }
        });

        running.start();
        TimeUnit.SECONDS.sleep(3);
        flag = true;
        running.join();
        System.out.println("runing is terminated");
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        MyLock myLock = new MyLock();
//        ReentrantLock lock = new ReentrantLock();
        for (int i = 0; i < 1000; i++) {
            executorService.execute(() -> {
                myLock.lock();
                cnt++;
                myLock.unlock();
            });
        }

        executorService.shutdown();
        if (executorService.isShutdown()) {
            executorService.awaitTermination(3, TimeUnit.SECONDS);
        }
        System.out.println(cnt);
    }

}

