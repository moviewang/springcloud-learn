package com.test.algorithm.sort;

/**
 * @Author: movie
 * @Date: 2021/4/14 16:12
 */
public class MergeSort {
    private static Comparable[] aux;

    public static void main(String[] args) {
        Comparable[] a = {1, 4, 6, 8, 2, 3};
        sort(a);
        for (Comparable comparable : a) {
            System.out.println(comparable);
        }
    }

    public static void sort(Comparable[] a) {
        aux = new Comparable[a.length];
        sort(a, 0, a.length - 1);

    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        int mid = lo + (hi - lo) / 2;
        sort(a, lo, mid);
        sort(a, mid + 1, hi);
        merge1(a, lo, mid, hi);
    }

    private static void merge(Comparable[] a, int lo, int mid, int hi) {
        int i = lo;
        int j = mid + 1;
        if (hi + 1 - lo >= 0) System.arraycopy(a, lo, aux, lo, hi + 1 - lo);
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                a[k] = aux[j++];
            } else if (j > hi) {
                a[k] = aux[i++];
            } else if (less(aux[j], aux[i])) {
                a[k] = aux[j++];
            } else {
                a[k] = aux[i++];
            }
        }
    }

    public static void merge1(Comparable[] a, int left, int mid, int right) {
        int i = left;
        int j = mid + 1;
        int k = left;
        while (i <= mid && j <= right) {
            if (less(a[i], a[j])) {
                aux[k++] = a[i++];
            } else {
                aux[k++] = a[j++];
            }
        }
        while (i <= mid) {
            aux[k++] = a[i++];
        }

        while (j <= right) {
            aux[k++] = a[j++];
        }
        for (int m = left; m <= right; m++) {
            a[m] = aux[m];
        }
    }

    private static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) <= 0;
    }
}
