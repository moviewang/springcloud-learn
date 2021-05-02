package com.test.skip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;

import java.util.Random;

/**
 * @Author: movie
 * @Date: 2021/3/27 13:47
 */
public class SkipList {
    @Autowired
    public static final int MAX_LEVEL = 16;
    private static final float SKIPLIST_P = 0.5f;
    private int levelCount = 1;
    //头链表
    private SkipNode head = new SkipNode();
    private Random random = new Random();


    public SkipNode find(int value) {
        SkipNode p = this.head;
        for (int i = levelCount - 1; i >= 0; i--) {
            while (p.forwards[i] != null && p.forwards[i].data < value) {
                p = p.forwards[i];
            }
        }
        if ((p.forwards[0] != null) && (p.forwards[0].data == value)) {
            return p.forwards[0];
        } else {
            return null;
        }
    }

    public void insert(int value) {
        int level = randomLevel();
        SkipNode skipNode = new SkipNode();
        skipNode.data = value;
        skipNode.maxLevel = level;
        SkipNode[] update = new SkipNode[level];
        for (int i = 0; i < level; i++) {
            update[i] = head;
        }
        SkipNode p = this.head;
        for (int i = level - 1; i >= 0; i--) {
            while (p.forwards[i] != null && p.forwards[i].data < value) {
                p = p.forwards[i];
            }
            update[i] = p;
        }
        for (int i = 0; i < level; i++) {
            skipNode.forwards[i] = update[i].forwards[i];
            update[i].forwards[i] = skipNode;
        }
        if (levelCount < level) {
            levelCount = level;
        }
    }

    public void delete(int value) {
        SkipNode[] update = new SkipNode[levelCount];
        SkipNode p = this.head;
        for (int i = levelCount; i >= 0; i--) {
            while (p.forwards[i] != null && p.forwards[i].data < value) {
                p = p.forwards[i];
            }
            update[i] = p;
        }
        if (p.forwards[0] != null && p.forwards[0].data == value) {
            for (int i = levelCount; i > 0; i--) {
                if (update[i].forwards[0] != null && update[i].forwards[0].data == value) {
                    update[i].forwards[i] = update[i].forwards[i].forwards[i];
                }
            }
        }
    }


    private int randomLevel() {
        int level = 1;
        while (Math.random() < SKIPLIST_P && level < MAX_LEVEL) {
            level += 1;
        }
        return level;
    }

    public void print() {
        SkipNode p = this.head;
        while (p.forwards[0] != null) {
            System.out.print(p.forwards[0] + " ");
            p = p.forwards[0];
        }
        System.out.println();
    }

    public static void main(String[] args) {

    }
}

