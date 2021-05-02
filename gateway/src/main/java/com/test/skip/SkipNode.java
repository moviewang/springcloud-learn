package com.test.skip;

import java.util.Arrays;

/**
 * @Author: movie
 * @Date: 2021/3/27 13:43
 */
public class SkipNode {
    public static final int MAX_LEVEL = 16;
    public int data = -1;
    public SkipNode[] forwards = new SkipNode[MAX_LEVEL];
    public int maxLevel = 0;

    @Override
    public String toString() {
        return "SkipNode{" +
                "data=" + data +
                ", forwards=" + Arrays.toString(forwards) +
                ", maxLevel=" + maxLevel +
                '}';
    }
}
