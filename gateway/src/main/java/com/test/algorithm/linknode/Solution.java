package com.test.algorithm.linknode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Stack;

/**
 * @Author: movie
 * @Date: 2020/4/24 16:18
 */
public class Solution {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.addNode(head, 1);
        head.addNode(head, 2);
        head.addNode(head, 3);
        head.addNode(head, 3);
        head.addNode(head, 4);
        head.addNode(head, 5);
        head.addNode(head, 5);
        head.removeNode(head, 2);
        head.traverse(head);
//        head.traverse(deleteDuplication(head));
        ListNode node = reverseNode(head);
        node.traverse(node);
        printListFromTailToHead2(head);

        ListNode test = new ListNode(3);
        ListNode node2 = test.addNode(test, 2);
        test.addNode(test, 0);
        ListNode node4 = test.addNode(test, -4);
//        node4.next = node2;
        EntryNodeOfLoop2(test);

    }

    public static ListNode deleteDuplication(ListNode pHead) {
        if (pHead == null || pHead.next == null) {
            return null;
        }
        ListNode pre = pHead;
        ListNode cur = pHead.next;
        HashSet<Integer> nodeSet = new HashSet<>();
        while (cur != null) {
            if (cur.val == pre.val) {
                nodeSet.add(cur.val);
            }
            pre = cur;
            cur = cur.next;
        }
        System.out.println(nodeSet);
        ListNode node = new ListNode(0);
        cur = pHead;
        while (cur != null) {
            if (!nodeSet.contains(cur.val)) {
                node.addNode(node, cur.val);
            }
            cur = cur.next;
        }
        return node.next;
    }

    public static ListNode deleteDuplication1(ListNode pHead) {
        if (pHead == null || pHead.next == null) {
            return pHead;
        }
        ListNode head = new ListNode(0);
        head.next = pHead;
        ListNode pre = head;
        ListNode cur = head.next;
        while (cur != null) {
            if (cur.next != null && cur.next.val == cur.val) {
                while (cur.next != null && cur.next.val == cur.val) {
                    cur = cur.next;
                }
                cur = cur.next;
                pre.next = cur;
            } else {
                pre = cur;
                cur = cur.next;
            }
        }
        return head.next;
    }

    private static ArrayList<Integer> list = new ArrayList<>();

    public ArrayList<Integer> printListFromTailToHead0(ListNode listNode) {
        if (listNode == null) {
            return list;
        }
        while (listNode != null) {
            list.add(0, listNode.val);
            listNode = listNode.next;
        }
        return list;
    }


    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        if (listNode == null) {
            return list;
        }
        ListNode cur = listNode;
        ListNode next = null;
        ListNode pre = null;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        while (pre != null) {
            list.add(pre.val);
            pre = pre.next;
        }
        return list;
    }


    public ArrayList<Integer> printListFromTailToHead1(ListNode listNode) {
        ArrayList<Integer> integers = new ArrayList<>();
        if (listNode == null) {
            return integers;
        }
        Stack<Integer> stack = new Stack<>();
        while (listNode != null) {
            stack.push(listNode.val);
            listNode = listNode.next;
        }
        while (!stack.isEmpty()) {
            integers.add(stack.pop());
        }
        return integers;
    }


    public static ArrayList<Integer> printListFromTailToHead2(ListNode listNode) {
        if (listNode != null) {
            printListFromTailToHead2(listNode.next);
            list.add(listNode.val);
        }
        return list;
    }

    public static ListNode reverseNode(ListNode pHead) {
        if (pHead == null) {
            return null;
        }
        ListNode cur = pHead;
        ListNode next;
        ListNode pre = null;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    public static ListNode reverseNode1(ListNode pHead) {
        if (pHead == null || pHead.next == null) {
            return null;
        }
        ListNode reverseHead = reverseNode1(pHead.next);
        pHead.next.next = pHead;
        pHead.next = null;
        return reverseHead;
    }

    public static ListNode reverseNodeRecursive(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = reverseNodeRecursive(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    public ListNode deleteDuplication2(ListNode pHead) {
        if (pHead == null) {
            return null;
        }
        //辅助头结点
        ListNode head = new ListNode(-1);
        head.next = pHead;
        ListNode pre = head;
        ListNode cur = head.next;
        while (cur != null) {
            if (cur.next != null && cur.val == cur.next.val) {
                while (cur.next != null && cur.val == cur.next.val) {
                    cur = cur.next;
                }
                cur = cur.next;
                pre.next = cur;
            }
            pre = cur;
            cur = cur.next;
        }
        return head.next;
    }

    public ListNode FindKthToTail(ListNode head, int k) {
        if (head == null || k < 1) {
            return null;
        }
        ListNode fast = head;
        ListNode slow = head;
        for (int i = 0; i < k; i++) {
            if (fast == null) {
                return null;
            }
            fast = fast.next;
        }
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

    public ListNode EntryNodeOfLoop(ListNode pHead) {
        if (pHead == null) {
            return null;
        }
        ListNode cur = pHead;
        HashSet<ListNode> nodes = new HashSet<>();
        while (cur != null) {
            if (nodes.contains(cur)) {
                return cur;
            }
            nodes.add(cur);
            cur = cur.next;
        }
        return null;
    }

    public static ListNode EntryNodeOfLoop2(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                break;
            }
        }
        if (fast == null) {
            return null;
        }
        ListNode l = head;
        while (l != fast) {
            l = l.next;
            fast = fast.next;
        }
        return l;
    }

    public ListNode EntryNodeOfLoop1(ListNode head) {
        boolean flag = false;
        if (head == null || head.next == null) {
            flag = false;
        }
        ListNode fast = head.next;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            if (fast == slow) {
                flag = true;
            }
            fast = fast.next.next;
            slow = slow.next;
        }
        if (!flag) {
            return null;
        }
        int circleLen = 0;
        ListNode next = fast.next;
        while (next != fast) {
            circleLen++;
            next = next.next;
        }
        ListNode r = head;
        for (int i = 0; i < circleLen; i++) {
            r = r.next;
        }
        ListNode l = head;
        while (l != r) {
            r = r.next;
            l = l.next;
        }
        return l;
    }

    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode cur = head;
        HashSet<ListNode> nodes = new HashSet<>();
        while (cur != null) {
            if (nodes.contains(cur)) {
                return true;
            }
            nodes.add(cur);
            cur = cur.next;
        }
        return false;
    }

    public boolean hasCycle1(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode fast = head.next;
        ListNode slow = head;
        while (fast != slow) {
            if (fast == null || fast.next == null) {
                return false;
            }
            fast = fast.next.next;
            slow = slow.next;
        }
        return true;
    }

    public ListNode Merge(ListNode list1, ListNode list2) {
        ListNode newHead = new ListNode(-1);
        ListNode cur = newHead;
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                cur.next = list1;
                list1 = list1.next;
            } else {
                cur.next = list2;
                list2 = list2.next;
            }
            cur = cur.next;
        }
        if (list1 == null) {
            cur.next = list1;
        }
        if (list2 == null) {
            cur.next = list2;
        }
        return newHead.next;
    }


    public ListNode Merge1(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        if (list1.val < list2.val) {
            list1.next = Merge(list1.next, list2);
            return list1;
        } else {
            list2.next = Merge(list2.next, list1);
            return list2;
        }
    }

    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        if (pHead1 == null || pHead2 == null) {
            return null;
        }
        HashSet<ListNode> nodes = new HashSet<>();
        while (pHead1 != null) {
            nodes.add(pHead1);
            pHead1 = pHead1.next;
        }
        while (pHead2 != null) {
            if (nodes.contains(pHead2)) {
                return pHead2;
            }
            pHead2 = pHead2.next;
        }
        return null;
    }

    public ListNode FindFirstCommonNode1(ListNode pHead1, ListNode pHead2) {
        if (pHead1 == null || pHead2 == null) {
            return null;
        }
        ListNode p1 = pHead1;
        ListNode p2 = pHead2;
        while (p1 != p2) {
            p1 = p1.next;
            p2 = p1.next;
            if (p1 != p2) {
                if (p1 == null) {
                    p1 = pHead2;
                }
                if (p2 == null) {
                    p2 = pHead1;
                }
            }
        }
        return p2;
    }

    public ListNode FindFirstCommonNode2(ListNode pHead1, ListNode pHead2) {
        if (pHead1 == null || pHead2 == null) {
            return null;
        }
        ListNode p1 = pHead1;
        int count1 = 0;
        while (p1 != null) {
            count1++;
            p1 = p1.next;
        }

        ListNode p2 = pHead2;
        int count2 = 0;
        while (p2 != null) {
            count2++;
            p2 = p2.next;
        }
        int temp = count1 - count2;
        ListNode fast = temp > 0 ? pHead1 : pHead2;
        ListNode slow = temp > 0 ? pHead2 : pHead1;
        for (int i = 0; i < temp; i++) {
            fast = fast.next;
        }
        while (fast != null) {
            if (fast.val == slow.val) {
                return fast;
            }
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }
}
