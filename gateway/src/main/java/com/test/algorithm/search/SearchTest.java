package com.test.algorithm.search;

/**
 * @Author: movie
 * @Date: 2020/6/5 22:19
 */
public class SearchTest {
    public static void main(String[] args) {
        int[] nums = {1, 2, 2, 2, 6, 7, 9};
        System.out.println(binarySearch(nums, 2));
        System.out.println(leftBound(nums, 2));
        System.out.println(rightBound(nums, 2));
    }


    public static int binarySearch(int[] nums, int key) {
        int i = 0;
        int j = nums.length - 1;
        while (i <= j) {
            int mid = i + (j - i) / 2;
            if (nums[mid] == key) {
                return mid;
            } else if (nums[mid] < key) {
                i = mid + 1;
            } else {
                j = mid - 1;
            }
        }
        return -1;
    }

    public static int leftBound(int[] nums, int key) {
        int i = 0;
        int j = nums.length - 1;
        while (i <= j) {
            int mid = i + (j - i) / 2;
            if (nums[mid] == key) {
                j = mid - 1;
            } else if (nums[mid] < key) {
                i = mid + 1;
            } else {
                j = mid - 1;
            }
        }
        if (i >= nums.length || nums[i] != key) {
            return -1;
        }
        return i;
    }

    public static int rightBound(int[] nums, int key) {
        int i = 0;
        int j = nums.length - 1;
        while (i <= j) {
            int mid = i + (j - i) / 2;
            if (nums[mid] == key) {
                i = mid + 1;
            } else if (nums[mid] < key) {
                i = mid + 1;
            } else {
                j = mid - 1;
            }
        }
        if (j < 0 || nums[j] != key) {
            return -1;
        }
        return j;
    }


//    public int binarySearch(int[] array, int k) {
//        int i = 0;
//        int j = array.length;
//        int mid = 0;
//        while (i <= j) {
//            mid = (i + j) / 2;
//            if (array[mid] == k) {
//                return mid;
//            } else if (array[mid] < k) {
//                i = mid + 1;
//            } else {
//                j = mid - 1;
//            }
//        }
//        return -1;
//    }
}
