package com.test.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: movie
 * @Date: 2020/5/26 16:44
 */
public class MyBlockQueue {
    private List<Integer> items = new ArrayList<>();
    private volatile int size;
    private volatile int capacity;
    private ReentrantLock lock = null;
    private Condition notEmpty = null;
    private Condition notFull = null;

    public MyBlockQueue(int capacity) {
        this.capacity = capacity;
        lock = new ReentrantLock();
        notEmpty = lock.newCondition();
        notFull = lock.newCondition();
    }

    public void offer(int e) throws InterruptedException {
        try {
            lock.lockInterruptibly();
            while (size >= capacity) {
                System.out.println("queue is full release lock");
                notFull.await();
            }
            size++;
            items.add(e);
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public int take() throws InterruptedException {
        try {
            lock.lockInterruptibly();
            while (size == 0) {
                System.out.println("queue is empty");
                notEmpty.await();
            }
            size--;
            Integer res = items.get(0);
            items.remove(0);
            notFull.signal();
            return res;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        MyBlockQueue queue = new MyBlockQueue(5);
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                try {
                    queue.offer(i);
                    System.out.println("producer" + i);
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(() -> {
            while (true) {
                try {
                    int res = queue.take();
                    System.out.println("take" + res);
                    TimeUnit.MILLISECONDS.sleep(600);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}

