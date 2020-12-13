package com.test.algorithm.linknode;

/**
 * @Author: movie
 * @Date: 2020/4/24 16:18
 */
public class ListNode {
    public ListNode next;
    public Integer val;

    public ListNode(Integer val) {
        this.val = val;
    }

    public ListNode addNode(ListNode head, Integer val) {
        ListNode node = new ListNode(val);
        if (head == null) {
            head = node;
        }
        ListNode cur = head;
        while (cur.next != null) {
            cur = cur.next;
        }
        cur.next = node;
        return node;
    }

    public void deleteNode(ListNode head, ListNode pTobeDeleted) {
        if (head == null || pTobeDeleted == null) {
            return;
        }
        if (pTobeDeleted.next != null) {
            ListNode next = pTobeDeleted.next;
            pTobeDeleted.next = next.next;
            pTobeDeleted.val = next.val;
            next = null;
        } else if (head == pTobeDeleted) {
            head = null;
            pTobeDeleted = null;
        } else {
            ListNode cur = head;
            while (cur != pTobeDeleted) {
                cur = cur.next;
            }
            cur.next = null;
            pTobeDeleted = null;
        }

    }

    public void removeNode(ListNode head, Integer val) {
        if (head == null) {
            return;
        }
        ListNode node = null;
        if (head.val == val) {
            node = head;
            head = head.next;
        }
        ListNode cur = head;
        while (cur.next != null && cur.next.val != val) {
            cur = cur.next;
        }
        if (cur.next != null && cur.next.val == val) {
            node = cur.next;
            cur.next = cur.next.next;
        }
        if (node != null) {
            node = null;
        }
    }



    public void traverse(ListNode head) {
        ListNode cur = head;
        while (cur != null) {
            System.out.print(cur.val + "\t");
            cur = cur.next;
        }
        System.out.println();
    }
}
