package com.test.algorithm;

import java.util.*;

/**
 * @Author: movie
 * @Date: 2020/4/7 10:34
 */
public class QueueTest {
    public static void main(String[] args) {
        PriorityQueue<Integer> min = new PriorityQueue<>();
        min.offer(1);
        min.offer(4);
        min.offer(2);
        min.offer(3);
        min.offer(5);
        Iterator<Integer> iterator = min.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        System.out.println(min.peek());
        min.poll();
        System.out.println(min.peek());
        byte a = 127;
        byte b = 127;
        System.out.println(a += b);
        HashMap<Object, Object> map = new HashMap<>();
        String key = "帅丙";
        System.out.println(key.hashCode());
        System.out.println(3 | 9);
        System.out.println(fn(5));
    }


    private PriorityQueue<Integer> max = new PriorityQueue<Integer>(11, new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2.compareTo(o1);
        }
    });

    public ArrayList<Integer> GetLeastNumbers_Solution(int[] input, int k) {
        ArrayList<Integer> integers = new ArrayList<>();
        if (input == null || input.length < 1) {
            return new ArrayList<>();
        }
        for (int i = 0; i < input.length; i++) {
            if (max.size() != k) {
                max.offer(input[i]);
            } else if (max.peek() < input[i]) {
                max.poll();
                max.offer(input[i]);
            }
        }
        integers.addAll(max);
        return integers;
    }

    public static int fn(int n) {
        if (n <= 2) {
            return n;
        }
        //dp[n] = dp[n-1] + dp[n-2]
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    public static int uniquePaths(int m, int n) {
        //dp[i][j] = dp[i-1][j] + dp[i][j-1]
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            dp[m][0] = 1;
        }
        for (int i = 0; i < n; i++) {
//            dp[]
        }
        return 0;
    }


}
