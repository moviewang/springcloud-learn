package com.test.thread;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: movie
 * @Date: 2020/5/26 17:46
 */
public class BoundBuffer {
    private ReentrantLock lock = new ReentrantLock();
    private Condition notFull = lock.newCondition();
    private Condition notEmpty = lock.newCondition();
    private volatile int putIndex;
    private int takeIndex;
    private int count;
    private Object[] items = new Object[100];

    public void put(Object e) throws InterruptedException {
        try {
            lock.lock();
            while (count == items.length) {
                notFull.await();
            }
            items[putIndex] = e;
            if (putIndex++ == items.length) {
                putIndex = 0;
            }
            count++;
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public Object take() throws InterruptedException {
        try {
            lock.lock();
            while (count == 0) {
                notEmpty.await();
            }
            Object item = items[takeIndex];
            items[takeIndex] = null;
            if (takeIndex++ == items.length) {
                takeIndex = 0;
            }
            count--;
            notFull.signal();
            return item;
        } finally {
            lock.unlock();
        }
    }

    private static volatile boolean flag = false;
    private static AtomicInteger num = new AtomicInteger();

    public static void main(String[] args) {
        new Thread(() -> {
            while (num.get() < 10) {
                System.out.println(Thread.currentThread().getName() + " " + num.incrementAndGet());
                flag = true;
            }
        }).start();

        new Thread(() -> {
            while (num.get() < 10) {
                System.out.println(Thread.currentThread().getName() + " " + num.incrementAndGet());
                flag = false;
            }
        }).start();
    }
}
