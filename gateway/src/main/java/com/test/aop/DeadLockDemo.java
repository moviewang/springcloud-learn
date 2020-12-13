package com.test.aop;

/**
 * @Author: movie
 * @Date: 2020/3/25 23:25
 */
public class DeadLockDemo {
    public static Object resource1 = new Object();
    public static Object resource2 = new Object();

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (resource1) {
                System.out.println(Thread.currentThread().getName() + "get resource1");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "wait resource2");
                synchronized (resource2) {
                    System.out.println(Thread.currentThread().getName() + "get resource2");
                }

            }
        }, "thread1").start();

        new Thread(() -> {
            synchronized (resource2) {
                System.out.println(Thread.currentThread().getName() + "get resource2");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "wait resource1");
                synchronized (resource1) {
                    System.out.println(Thread.currentThread().getName() + "get resource1");
                }

            }
        }, "thread2").start();
    }
}
