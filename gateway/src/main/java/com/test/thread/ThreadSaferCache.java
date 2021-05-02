package com.test.thread;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @Author: movie
 * @Date: 2021/3/30 14:51
 */
public class ThreadSaferCache {
    private volatile int result;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadSaferCache threadSaferCache = new ThreadSaferCache();
        for (int i = 0; i < 8; i++) {
            new Thread(() -> {
                int x = 0;
                while (threadSaferCache.getResult() < 100) {
                    x++;
                }
                System.out.println(x);
            }).start();
        }
        TimeUnit.SECONDS.sleep(1);
        threadSaferCache.setResult(200);

        Thread thread1 = new Thread(new Condition());
        Thread thread2 = new Thread(new Condition());
        thread1.start();
        thread2.start();
        while (thread1.isAlive() || thread2.isAlive()) {

        }
        System.out.println("test finish");
    }
}


class Condition implements Runnable {

    @Override
    public void run() {
        if ("Thread-9".equals(Thread.currentThread().getName())) {
            try {
                method0();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if ("Thread-10".equals(Thread.currentThread().getName())) {
            try {
                method1();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        ConcurrentHashMap<String, String> result = new ConcurrentHashMap<>();
    }

    public synchronized void method1() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " running");
        TimeUnit.SECONDS.sleep(4);
        System.out.println(Thread.currentThread().getName() + " completed");
    }

    public synchronized void method0() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " running");
        TimeUnit.SECONDS.sleep(4);
        System.out.println(Thread.currentThread().getName() + " throw exception release lock");
        throw new RuntimeException();
    }
}

