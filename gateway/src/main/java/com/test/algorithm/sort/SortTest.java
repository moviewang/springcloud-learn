package com.test.algorithm.sort;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @Author: movie
 * @Date: 2020/6/9 22:38
 */
public class SortTest {
    public static void main(String[] args) {
        int[] a = {1, 3, 5, 7};
        int[] b = {2, 4, 6, 8};
        int[] c = new int[a.length + b.length];
        mergeArray(a, a.length, b, b.length, c);
        for (int i : c) {
            System.out.println(i);
        }
        System.out.println("----------");
        int[] arr = {2, 4, 3, 6, 7, 1, 8};
        int[] result = new int[arr.length];
//        mergeSort(arr, result, 0, arr.length - 1);
        heapSort(arr);
        for (int i : arr) {
            System.out.println(i);
        }

    }

    public static void mergeArray(int[] a, int m, int[] b, int n, int[] c) {
        int i = 0;
        int j = 0;
        int k = 0;
        while (i < m && j < n) {
            if (a[i] < b[j]) {
                c[k++] = a[i++];
            } else {
                c[k++] = b[j++];
            }
        }
        while (i < m) {
            c[k++] = a[i++];
        }
        while (j < n) {
            c[k++] = b[j++];
        }
    }

    public static void mergeArray(int[] a, int[] result, int left, int mid, int right) {
        int i = left;
        int m = mid;
        int j = mid + 1;
        int n = right;
        int k = left;
        while (i <= m && j <= n) {
            if (a[i] <= a[j]) {
                result[k++] = a[i++];
            } else {
                result[k++] = a[j++];
            }
        }
        while (i <= m) {
            result[k++] = a[i++];
        }
        while (j <= n) {
            result[k++] = a[j++];
        }
        for (int s = left; s <= right; s++) {
            a[s] = result[s];
        }
    }

    public static void mergeSort(int[] a, int[] result, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(a, result, left, mid);
            mergeSort(a, result, mid + 1, right);
            mergeArray(a, result, left, mid, right);
        }
    }

    public int findMin(int[] numbers) {
        if (numbers == null || numbers.length < 1) {
            return -1;
        }
        int i = 0;
        int j = numbers.length - 1;
        while (i < j) {
            int mid = i + (j - i) / 2;
            if (numbers[mid] > numbers[j]) {
                i = mid + 1;
            } else if (numbers[mid] < numbers[j]) {
                j = mid;
            } else {
                j = j - 1;
            }
        }
        return numbers[i];
    }

    public static void heapSort(int[] nums) {
        PriorityQueue<Integer> max = new PriorityQueue<>(Comparator.naturalOrder());
        for (int num : nums) {
            max.offer(num);
        }
        int k = 0;
        while (!max.isEmpty()) {
            nums[k++] = max.poll();
        }
    }

}