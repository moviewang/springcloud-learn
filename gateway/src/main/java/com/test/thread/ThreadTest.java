package com.test.thread;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.concurrent.*;

/**
 * @Author: movie
 * @Date: 2020/3/28 13:48
 */
public class ThreadTest {
    private static final int threadCount = 550;
    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(5, () -> {
        System.out.println("------当线程数达到之后，优先执行------");
    });

    public static void main(String[] args) throws InterruptedException {
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
        for (ThreadInfo threadInfo : threadInfos) {
            System.out.println(threadInfo.getThreadId() + "\t" + threadInfo.getThreadName());
        }
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        final Semaphore semaphore = new Semaphore(20);
        CountDownLatch countDownLatch = new CountDownLatch(threadCount);
        for (int i = 0; i < threadCount; i++) {
            int finalI = i;
            TimeUnit.SECONDS.sleep(1);
            executorService.execute(() -> {
                try {
//                    semaphore.acquire();
                    test(finalI);
//                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
//                    countDownLatch.countDown();
                }

            });
        }
//        countDownLatch.await();
        executorService.shutdown();
        System.out.println("finish");
        TJavaThread tJavaThread = new TJavaThread();
        tJavaThread.start();
        tJavaThread.join();
        System.out.println(tJavaThread.getCount());
    }

    public static void test(int i) throws InterruptedException {
        System.out.println("threadnum:" + i + "is ready");
        try {
            cyclicBarrier.await(60, TimeUnit.SECONDS);
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        System.out.println("threadnum:" + i + "is finish");
    }

    static class TJavaThread extends Thread {
        static int count = 0;

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                count++;
            }
        }

        public int getCount() {
            return count;
        }

        public static void setCount(int count) {
            TJavaThread.count = count;
        }
    }
}

