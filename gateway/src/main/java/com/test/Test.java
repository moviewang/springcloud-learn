package com.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Author: movie
 * @Date: 2020/2/29 16:26
 */
public class Test {
    public static void main(String[] args) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(1);
        System.out.println(stringBuffer.toString());
        ArrayList<Integer> list = new ArrayList<>();
        int[] a = new int[10];
        for (int i = 0; i < 4; i++) {
            a[i] = i;
        }
        System.arraycopy(a, 2, a, 3, 3);
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
        int[] b = Arrays.copyOf(a, 12);
        for (int i = 0; i < b.length; i++) {
            System.out.print(b[i] + "\t");
        }

        System.out.println();
        CopyOnWriteArrayList<String> strings = new CopyOnWriteArrayList<>();
        strings.add("11");
        strings.add("22");
        Iterator<String> iterator = strings.iterator();
        //copyOnWriteArrayList的弱一致性 不会输出33
        strings.add("33");
        System.out.println(strings.size());
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        strings.forEach(System.out::print);

    }
}

