package com.test.thread;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Author: movie
 * @Date: 2020/5/2 11:38
 */
public class SimpleDateFormatTest {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (;;) {
            executorService.execute(() -> {
                try {
                    TimeUnit.MILLISECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    String format = sdf.format(new Date(Math.abs(new Random().nextLong())));
                    System.out.println(format);
                } catch (Exception e){
                    e.printStackTrace();
                    System.exit(1);
                }
            });
        }
    }
}
