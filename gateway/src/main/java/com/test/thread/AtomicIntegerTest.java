package com.test.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: movie
 * @Date: 2020/3/28 23:18
 */
public class AtomicIntegerTest {
    private static AtomicInteger count = new AtomicInteger();
    public static int sum = 0;

    public static void setSum() {
        sum++;
    }

    public static void increment() {
        count.incrementAndGet();
    }

    public int count() {
        return count.get();
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 1000; i++) {
            executorService.execute(() -> {
                setSum();
                increment();
            });
        }
        executorService.shutdown();
        try {
            executorService.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (executorService.isShutdown()) {
            System.out.println(sum);
            System.out.println(count.get());
        }
        defectABA();
    }

    static void defectABA() {
        final AtomicInteger atomicInteger = new AtomicInteger(1);
        Thread coreThread = new Thread(() -> {
            int currentVal = atomicInteger.get();
            System.out.println(Thread.currentThread().getName() + " ------ currentValue=" + currentVal);
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean casResult = atomicInteger.compareAndSet(1, 2);
            System.out.println(Thread.currentThread().getName()
                    + " ------ currentValue=" + currentVal
                    + ", finalValue=" + atomicInteger.get()
                    + ", compareAndSet Result=" + casResult);

        });
        coreThread.start();
        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Thread amateurThread = new Thread(() -> {
            int currentVal = atomicInteger.get();
            boolean casResult = atomicInteger.compareAndSet(1, 2);
            System.out.println(Thread.currentThread().getName()
                    + " ------ currentValue=" + currentVal
                    + ", finalValue=" + atomicInteger.get()
                    + ", compareAndSet Result=" + casResult);

            currentVal = atomicInteger.get();
            casResult = atomicInteger.compareAndSet(2, 1);
            System.out.println(Thread.currentThread().getName()
                    + " ------ currentValue=" + currentVal
                    + ", finalValue=" + atomicInteger.get()
                    + ", compareAndSet Result=" + casResult);

        });
        amateurThread.start();
    }
}
