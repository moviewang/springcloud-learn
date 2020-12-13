package com.test.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: movie
 * @Date: 2020/5/17 12:52
 */
public class ReentrantLockTest {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        new Thread(() -> {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "get lock");
                System.out.println(Thread.currentThread().getName() + "await");
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "wake up");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
                System.out.println(Thread.currentThread().getName() + "release lock");

            }
        }).start();

        new Thread(() -> {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "get lock");
                condition.signal();
                System.out.println(Thread.currentThread().getName() + "wake up thread-1");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                System.out.println(Thread.currentThread().getName() + "release lock");
                lock.unlock();
            }
        }).start();
    }
}
