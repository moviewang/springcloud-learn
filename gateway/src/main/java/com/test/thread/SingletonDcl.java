package com.test.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: movie
 * @Date: 2021/3/22 17:48
 */
public class SingletonDcl {
    private static volatile SingletonDcl singletonDcl = null;
    private static ReentrantLock lock = new ReentrantLock();
    private static Condition c1 = lock.newCondition();
    private static Condition c2 = lock.newCondition();
    private static CountDownLatch count = new CountDownLatch(1);
    private static final ThreadLocal<SingletonDcl> map = new ThreadLocal<>();

    private SingletonDcl() {
    }

    public static SingletonDcl getInstance() {
        if (singletonDcl == null) {
            synchronized (SingletonDcl.class) {
                if (singletonDcl == null) {
                    singletonDcl = new SingletonDcl();
                }
            }
        }
        return singletonDcl;
    }

    public static SingletonDcl getInstance1() {
        SingletonDcl singletonDcl = map.get();
        if (singletonDcl == null) {
            singletonDcl = new SingletonDcl();
            map.set(singletonDcl);
        }
        return singletonDcl;
    }

    public static void main(String[] args) {
        String c = "abcdef";
        String n = "12345";
        char[] ca = c.toCharArray();
        char[] na = n.toCharArray();
        Thread t1 = new Thread(() -> {
            count.countDown();
            try {
                lock.lock();
                for (char caa : ca) {
                    c1.signal();
                    System.out.println(caa);
                    c2.await();
                }
                c1.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                count.await();
                lock.lock();
                for (char naa : na) {
                    c2.signal();
                    System.out.println(naa);
                    c1.await();
                }
                c2.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });
        t1.start();
        t2.start();
        SingletonDcl instance1 = getInstance1();
        SingletonDcl instance2 = getInstance1();
        System.out.println(instance1 == instance2);
        new Thread(() -> {
            SingletonDcl instance3 = getInstance1();
            System.out.println(instance1 == instance3);
        }).start();

    }

}
