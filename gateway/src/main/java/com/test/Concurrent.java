package com.test;

import java.util.BitSet;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: movie
 * @Date: 2020/3/29 15:42
 */
public class Concurrent {
    public static void main(String[] args) throws InterruptedException {
        Map<String, String> map = new ConcurrentSkipListMap();
        map.put("a", "1");
        map.put("b", "12");
        map.put("aa", "3");
        map.put("ba", "4");
        map.forEach((k, v) -> System.out.println(String.format("%s:%s", k, v)));
//        MyBlockQueue myBlockQueue = new MyBlockQueue(5);
//        int len = 20;
//        new Thread(() -> {
//            for (int i = 0; i < len; i++) {
//                try {
//                    TimeUnit.MILLISECONDS.sleep(300);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                myBlockQueue.put(i);
//                System.out.println("produce:" + i);
//            }
//        }).start();
//
//        new Thread(() -> {
//            for (int i = 0; i < len; i++) {
//                try {
//                    TimeUnit.SECONDS.sleep(1);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                try {
//                    myBlockQueue.take();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.println("take:" + i);
//            }
//        }).start();
//        while (Thread.activeCount() > 1) {
//            Thread.yield();
//        }
//        System.out.println(myBlockQueue.size());

        PrintStringA printStringA = new PrintStringA();
        Thread threadA = new Thread(printStringA.getRunnableA());
        Thread threadB = new Thread(printStringA.getRunnableB());
        threadA.start();
        threadB.start();
        threadA.join();
        threadB.join();

        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        new Thread(() -> {
            lock.lock();
            try {
                System.out.println("A get lock");
                System.out.println("A wait");
                condition.await();
                System.out.println("A is alive");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }).start();
        new Thread(() -> {
            lock.lock();
            System.out.println("B get lock");
            System.out.println("B wait");
            condition.signal();
            System.out.println("B release lock");
            lock.unlock();
        }).start();
//        ExecutorService pool = Executors.newCachedThreadPool();
//        for (int i = 0; i < 100; i++) {
//            pool.execute(() -> {
//                try {
//                    TimeUnit.SECONDS.sleep(1);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.println(Singleton.INSTANCE);
//            });
//        }
        int[] a = {1, 35, 1};
        int[] b = a;
        System.out.println(a);
        for (int i = 0; i < b.length; i++) {
            System.out.println(b[i]);
        }
        String s3 = new String("ab") + new String("cd");
        String s5 = s3.intern();
        String s4 = "abcd";
        System.out.println(s3 == s4);
        System.out.println(s5 == s4);

        VolatileTest volatileTest = new VolatileTest();
        Thread thread1 = new Thread(volatileTest);
        Thread thread2 = new Thread(volatileTest);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(volatileTest.sum);
        CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "：" + "准备化妆");
            return "完事了";
        }).handleAsync((result, exception) -> {
            if (exception != null) {
                return exception.getCause();
            } else {
                return result;
            }
        }).thenApplyAsync(returnStr -> {
            System.out.println(Thread.currentThread().getName() + ":" + returnStr);
            return returnStr;
        });
        System.out.println(Thread.currentThread().getName() + ":" + "自己玩去了");
        int[] bitArray = {1, 2, 3, 4, 5};
        BitSet bitSet = new BitSet(5);
        for (int i = 0; i < 5; i++) {
            bitSet.set(bitArray[i], true);
        }
        System.out.println(bitSet.size());
        for (int i = 1; i < 7; i++) {
            System.out.println(bitSet.get(i));
        }

    }


    static class MyBlockQueue {
        private Object[] items;
        private int putIndex;
        private int takeIndex;
        private int count;
        private Condition notFull;
        private Condition notEmpty;
        private ReentrantLock lock;

        public MyBlockQueue(int cap) {
            if (cap <= 0) {
                throw new IllegalArgumentException();
            }
            this.items = new Object[cap];
            this.lock = new ReentrantLock();
            notFull = lock.newCondition();
            notEmpty = lock.newCondition();
        }

        public void put(Object element) {
            Objects.requireNonNull(element);
            ReentrantLock lock = this.lock;
            lock.lock();
            try {
                while (count == items.length) {
                    notFull.await();
                }
                enqueue(element);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }

        private void enqueue(Object element) {
            Object[] items = this.items;
            items[putIndex] = element;
            while (++putIndex == items.length) {
                putIndex = 0;
            }
            count++;
            notEmpty.signal();
        }

        public Object take() throws InterruptedException {
            ReentrantLock lock = this.lock;
            try {
                lock.lock();
                while (count == 0) {
                    notEmpty.await();
                }
                return dequeue();
            } finally {
                lock.unlock();
            }
        }

        private Object dequeue() {
            Object[] items = this.items;
            Object element = items[takeIndex];
            items[takeIndex] = null;
            while (++takeIndex == items.length) {
                takeIndex = 0;
            }
            count--;
            notFull.signal();
            return element;
        }

        public int size() {
            ReentrantLock lock = this.lock;
            try {
                lock.lock();
                return count;
            } finally {
                lock.unlock();
            }
        }
    }

    static class PrintStringA {
        private static volatile boolean open;
        private static volatile int index;
        private static String s = "abcdefghijkl";

        public static Runnable runnableA;
        public static Runnable runnableB;

        public PrintStringA() {
            runnableA = () -> {
                while (index < s.length()) {
                    if (open) {
                        System.out.println(Thread.currentThread().getName() + ":" + s.charAt(index++));
                        open = false;
                    }
                }
            };
            runnableB = () -> {
                while (index < s.length()) {
                    if (!open) {
                        System.out.println(Thread.currentThread().getName() + ":" + s.charAt(index++));
                        open = true;
                    }
                }
            };
        }

        public static Runnable getRunnableA() {
            return runnableA;
        }

        public static Runnable getRunnableB() {
            return runnableB;
        }
    }

}

class VolatileTest implements Runnable {
    volatile int sum = 0;

    @Override
    public void run() {
        try {
            TimeUnit.MILLISECONDS.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 1000; i++) {
            sum++;
        }
    }
}

enum Singleton {
    INSTANCE;
}


