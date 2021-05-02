package com.test.thread;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.threadpool.TtlExecutors;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

import java.util.IntSummaryStatistics;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

/**
 * @Author: movie
 * @Date: 2020/12/15 19:08
 */
public class InheritableThreadLocalTest {
    private static final Integer SIZE = 500;

    static class LocalVariable {
        private byte[] local = new byte[5 * 1024 * 1024];
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService pool = TtlExecutors.getTtlExecutorService(Executors.newSingleThreadExecutor());
//        ThreadLocal<Integer> threadLocal = new TransmittableThreadLocal<>();
        ThreadLocal<Stu> threadLocal = new TransmittableThreadLocal<>();
        System.out.println("main thread start");
        threadLocal.set(new Stu(1));
        pool.execute(() -> {
            System.out.println(Thread.currentThread().getName() + ":" + threadLocal.get());
        });
        threadLocal.get().setAge(2);
        pool.execute(() -> {
            System.out.println(Thread.currentThread().getName() + ":" + threadLocal.get());
            threadLocal.get().setAge(3);
        });
        System.out.println(threadLocal.get());
        pool.shutdown();
        pool.awaitTermination(3, TimeUnit.SECONDS);

        HashFunction hashFunction = Hashing.murmur3_32();
        System.out.println(hashFunction.hashInt(1).asInt());
        System.out.println(hashFunction.hashInt(10).asInt());
        IntSummaryStatistics statistics = Stream.of(1, 2, 3, 4).mapToInt(Integer::intValue).summaryStatistics();

    }
}

class Stu {
    int age = 1;

    public Stu(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Stu{" +
                "age=" + age +
                '}';
    }
}

