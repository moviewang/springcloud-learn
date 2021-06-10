package com.test.algorithm.linknode;

import java.util.*;

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


    List<Integer> res = new LinkedList<>();

    public int[] reversePrint(ListNode head) {

        if (head == null) {
            return new int[]{};
        }
        reversePrintHelp(head);
        int[] arr = new int[res.size()];
        int cnt = 0;
        for (Integer num : res) {
            arr[cnt++] = num;
        }
        return arr;
    }

    void reversePrintHelp(ListNode head) {
        if (head != null) {
            reversePrint(head.next);
            res.add(head.val);
        }
    }

    ListNode reverse(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;

//        ListNode cur = head;
//        ListNode pre = null;
//        while (cur != null) {
//            ListNode next = cur.next;
//            next.next = cur;
//            cur.next = pre;
//            pre = cur;
//            cur = next;
//        }
//        return pre;
    }

    public ListNode deleteNode(ListNode head, int val) {
        if (head == null) {
            return null;
        }
        if (head.val == val) {
            head = head.next;
        }
        ListNode cur = head;
        ListNode pre = null;
        ListNode next = null;
        while (cur != null) {
            next = cur.next;
            if (cur.val == val) {
                pre.next = next;
            }
            pre = cur;
            cur = next;
        }
        return head;
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode curA = headA;
        ListNode curB = headB;
        while (curA != curB) {
            curA = curA == null ? curB : curA.next;
            curB = curB == null ? curA : curB.next;
        }
        return curA;


//        int cntA = 0;
//        int cntB = 0;
//        ListNode curA = headA;
//        while (curA != null) {
//            cntA++;
//            curA = curA.next;
//        }
//
//        ListNode curB = headA;
//        while (curB != null) {
//            cntB++;
//            curB = curA.next;
//        }
//        boolean flag = cntA - cntB > 0 ? true : false;
//        int step = Math.abs(cntA - cntB);
//        if (flag) {
//            for (int i = 0; i < step; i++) {
//                headA = headA.next;
//            }
//        } else {
//            for (int i = 0; i < step; i++) {
//                headB = headB.next;
//            }
//        }
//        while (headA != null && headB != null) {
//            if (headA.next == headB.next) {
//                return headA.next;
//            }
//            headA = headA.next;
//            headB = headB.next;
//        }
//        return null;
    }

    public ListNode getKthFromEnd(ListNode head, int k) {
        if (head == null || k <= 0) {
            return null;
        }
        ListNode fast = head;
        for (int i = 0; i < k; i++) {
            fast = fast.next;
        }
        ListNode slow = head;
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }


    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        Node cur = head;
        Map<Node, Node> map = new HashMap<>();
        while (cur != null) {
            map.put(cur, new Node(cur.val));
            cur = cur.next;
        }
        cur = head;
        while (cur != null) {
            map.get(cur).next = map.get(cur.next);
            map.get(cur).random = map.get(cur.random);
            cur = cur.next;
        }
        return map.get(head);
    }

    ListNode head;

    public void deleteNode(ListNode node) {
        node = node.next;
        node.val = node.next.val;
    }

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }
        HashSet<Integer> set = new HashSet<>();
        ListNode cur = head;
        while (cur != null) {
            if (set.contains(cur.val)) {
                if (cur.next != null) {
                    cur.val = cur.next.val;
                    cur.next = cur.next.next;
                } else {
                    ListNode pre = head;
                    while (pre.val != cur.val) {
                        pre = pre.next;
                    }
                    pre.next = null;
                    return head;
                }
                cur = cur.next;
            } else {
                set.add(cur.val);
                cur = cur.next;
            }
        }
        return head;
    }

    public ListNode deleteDuplicates1(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode cur = head;
        while (cur.next != null) {
            if (cur.val == cur.next.val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return head;
    }

    ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        cur.next = l1 == null ? l2 : l1;
        return dummy.next;
    }


}


