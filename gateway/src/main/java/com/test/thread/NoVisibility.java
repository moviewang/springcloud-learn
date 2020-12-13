package com.test.thread;

/**
 * @Author: movie
 * @Date: 2020/5/23 13:15
 */
public class NoVisibility {
    private static boolean ready;
    private static int num;

    private static class Reader extends Thread {
        @Override
        public void run() {
            while (!ready) {
                //交出当前线程的执行权
                Thread.yield();
            }
            System.out.println(num);
        }
    }

    public static void main(String[] args) {
        new Reader().start();
        ready = true;
        num = 42;
    }
}
