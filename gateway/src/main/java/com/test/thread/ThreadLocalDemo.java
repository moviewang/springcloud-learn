package com.test.thread;

import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @Author: movie
 * @Date: 2020/3/28 17:05
 */
public class ThreadLocalDemo implements Runnable {
    private static final ThreadLocal<SimpleDateFormat> FORMAT_THREAD_LOCAL = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyyMMdd HHmm"));
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat();

    @Override
    public void run() {
        System.out.println("Thread Name= " + Thread.currentThread().getName() + " default Formatter = " + FORMAT_THREAD_LOCAL.get().toPattern());
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        FORMAT_THREAD_LOCAL.set(new SimpleDateFormat());
        System.out.println("Thread Name= " + Thread.currentThread().getName() + " default Formatter = " + FORMAT_THREAD_LOCAL.get().toPattern());
    }

    public static void main(String[] args) {
        ThreadLocalDemo threadLocalDemo = new ThreadLocalDemo();
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(threadLocalDemo, i + "");
            try {
                TimeUnit.SECONDS.sleep(new Random().nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            thread.start();
        }
    }
}
