package com.test.algorithm;

import com.test.algorithm.linknode.ListNode;

import java.util.*;

/**
 * @Author: movie
 * @Date: 2020/4/1 20:57
 */
public class Solution {
    private PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    private PriorityQueue<Integer> maxHeap = new PriorityQueue<>(15, new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2.compareTo(o1);
        }
    });

    public static void main(String[] args) {
        System.out.println(hammingWeight1(11));
        StringBuilder stringBuilder = new StringBuilder();
    }

    public static void reOrderArray(int[] array) {
        if (array == null || array.length < 1) {
            return;
        }
        ArrayList<Integer> odds = new ArrayList<>();
        ArrayList<Integer> evens = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            if ((array[i] & 1) == 0) {
                odds.add(array[i]);
            } else {
                evens.add(array[i]);
            }
        }
        odds.addAll(evens);
        for (int i = 0; i < odds.size(); i++) {
            array[i] = odds.get(i);
        }
    }

    public static List<List<Integer>> generator(int numRows) {
        ArrayList<List<Integer>> list = new ArrayList<>();
        if (numRows < 1) {
            return list;
        }
        list.add(new ArrayList<>());
        list.get(0).add(1);
        for (int i = 1; i < numRows; i++) {
            ArrayList<Integer> row = new ArrayList<>();
            List<Integer> preRow = list.get(i - 1);
            row.add(1);
            for (int j = 1; j < i; j++) {
                row.add(preRow.get(j - 1) + preRow.get(j));
            }
            row.add(1);
            list.add(row);
        }
        return list;
    }

    public double Power(double base, int exponent) {
        if (base == 0) {
            return 0L;
        }
        if (exponent == 0) {
            return 1;
        }
        boolean flag = exponent < 0;
        exponent = flag ? -exponent : exponent;
        double sum = 1D;
        for (int i = 0; i < exponent; i++) {
            sum *= base;
        }
        return flag ? 1 / sum : sum;
    }

    private int count;

    private List<Integer> nums = new ArrayList<>();

    public void Insert(Integer num) {
        if (num == null) {
            return;
        }
        count++;
        nums.add(num);
    }

    public Double GetMedian() {
        Collections.sort(nums);
        if ((count & 1) == 1) {
            int index = (count + 1) / 2 - 1;
            return Double.valueOf(nums.get(index));
        } else {
            int num1 = count / 2;
            return Double.valueOf((nums.get(num1 - 1) + nums.get(num1)) / 2D);
        }
    }

    public int GetNumberOfK(int[] array, int k) {
        if (array == null) {
            return -1;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < array.length; i++) {
            if (map.containsKey(array[i])) {
                Integer count = map.get(array[i]);
                map.put(array[i], count + 1);
            } else {
                map.put(array[i], 1);
            }
        }
        return map.get(k) == null ? -1 : map.get(k);
    }

    int cnt = 0;

    public int GetNumberOfK1(int[] array, int k) {
        if (array == null) {
            return 0;
        }
        int lower = lowerBound(array, k);
        int upper = upperBound(array, k);
        return upper - lower;
    }

    private int upperBound(int[] array, int k) {
        int i = 0, j = array.length - 1, mid = 0;
        while (i <= j) {
            mid = (i + j) >> 1;
            if (array[mid] <= k) {
                i = mid + 1;
            } else {
                j = mid - 1;
            }
        }
        return i;
    }

    private int lowerBound(int[] array, int k) {
        int i = 0, j = array.length - 1, mid = 0;
        while (i <= j) {
            mid = (i + j) >> 1;
            if (array[mid] < k) {
                i = mid + 1;
            } else {
                j = mid - 1;
            }
        }
        return i;
    }

    public int Add(int num1, int num2) {
        return 0;
    }

    public String LeftRotateString(String str, int n) {
        if (str == null || str.length() < 1) {
            return "";
        }
        String pre = str.substring(0, n);
        String post = str.substring(n);
        return post + pre;
    }

    public ArrayList<Integer> FindNumbersWithSum(int[] array, int sum) {
        ArrayList<Integer> res = new ArrayList<>();
        if (array == null || array.length < 1) {
            return res;
        }
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < array.length; i++) {
            set.add(array[i]);
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < array.length; i++) {
            int multi = sum - array[i];
            if (set.contains(multi) && min > (array[i] * (multi))) {
                min = array[i] * (multi);
                if (array[i] < multi) {
                    res.add(0, array[i]);
                    res.add(1, multi);
                } else {
                    res.add(0, multi);
                    res.add(1, array[i]);
                }
            }
        }
        return res;
    }


    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode reverseHead = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return reverseHead;
    }

    public String reverseLeftWords(String s, int n) {
        if (s == null || s.length() < 1) {
            return "";
        }
        char[] chars = s.toCharArray();
        swap(chars, 0, n - 1);
        swap(chars, n, s.length() - 1);
        swap(chars, 0, s.length() - 1);
        return new String(chars);
    }

    public static char[] swap(char[] chars, int i, int j) {
        while (i < j) {
            char temp = chars[i];
            chars[i] = chars[j];
            chars[j] = temp;
            i++;
            j--;
        }
        return chars;
    }

    public int reversePairs(int[] nums) {
        if (nums == null || nums.length < 1) {
            return 0;
        }

        int cnt = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] > nums[j]) {
                    cnt++;
                }
            }
        }
        return cnt;
    }

    public int fib(int n) {
        if (n < 2) {
            return n;
        }
        int a = 0;
        int b = 1;
        int c = 0;
        for (int i = 2; i <= n; i++) {
            c = a + b;
            a = b;
            b = c;
        }
        return c % 1000000007;
    }

    public double[] twoSum(int n) {
        double[] pre = {1 / 6d, 1 / 6d, 1 / 6d, 1 / 6d, 1 / 6d, 1 / 6d};
        for (int i = 2; i <= n; i++) {
            double[] temp = new double[5 * i + 1];
            for (int j = 0; j < pre.length; j++) {
                for (int x = 0; x < 6; x++) {
                    temp[j + x] += pre[j] / 6;
                }
            }
            pre = temp;
        }
        return pre;
    }

    public int hammingWeight(int n) {
        n = n < 0 ? -n : n;
        int count = 0;
        while (n > 0) {
            n = n & (n - 1);
            count++;
        }
        return count;
    }

    public static int hammingWeight1(int n) {
        int count = 0;
        int temp = 1;
        while (temp != 0) {
            if ((n & temp) > 0) {
                count++;
            }
            temp >>>= 1;
        }
        return count;
    }

}

class MinStack {

    private Stack<Integer> min = new Stack<>();
    private Stack<Integer> stack = new Stack<>();

    /**
     * initialize your data structure here.
     */
    public MinStack() {

    }

    public void push(int x) {
        stack.push(x);
        if (min.isEmpty()) {
            min.push(x);
        } else {
            int minVal = Math.min(this.min.peek(), x);
            min.push(minVal);
        }
    }

    public void pop() {
        stack.pop();
        min.pop();
    }


    public int top() {
        return stack.peek();
    }

    public int min() {
        return min.peek();
    }
}
