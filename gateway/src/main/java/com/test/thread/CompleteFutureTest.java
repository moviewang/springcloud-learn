package com.test.thread;

import java.util.concurrent.CompletableFuture;

/**
 * @Author: movie
 * @Date: 2020/5/15 12:57
 */
public class CompleteFutureTest {
    public static void main(String[] args) {
        CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> {
            throw new RuntimeException();
        }).exceptionally(ex -> "error reusltA")
                .thenApply((resultA) -> resultA + "resultB")
                .thenApply((resultB) -> {
                    throw new RuntimeException();
                })
                .handleAsync((r, e)-> {
                    if (e != null) {
                        return "errorC";
                    }
                    return r;
                })
                .thenApply((resultC) -> resultC + "resultD");
        System.out.println(cf.join());

        CompletableFuture<String> cfA = CompletableFuture.supplyAsync(() -> "A");
        CompletableFuture<String> cfB = CompletableFuture.supplyAsync(() -> "B");
        CompletableFuture<String> cfC = CompletableFuture.supplyAsync(() -> "C");
        CompletableFuture<String> mergeFuture = cfA.thenCombine(cfB, (a, b) -> a + b);
        System.out.println(mergeFuture.join());

        CompletableFuture<String> combine = cfA.thenCombine(cfB, (a, b) -> a + b)
                .thenCombine(cfC, (ab, c) -> ab + c);
        System.out.println(combine.join());
        CompletableFuture<String> compose = cfA.thenCompose(a -> cfB)
                .thenCompose(ab -> cfC);
        System.out.println(compose.join());

    }
}
