package com.test.algorithm.linknode;

/**
 * @Author: movie
 * @Date: 2021/5/9 16:10
 */
public class MyLinkList<T> {
    class Node<T> {
        T data;
        Node pre;
        Node next;

        public Node() {

        }

        public Node(T data) {
            this.data = data;
        }
    }

    private Node head;
    private Node tail;
    private int length;

    public boolean isEmpty() {
        return length == 0;
    }

    public MyLinkList() {
        this.head = null;
        tail = head;
        length = 0;
    }

    void addFirst(T data) {
        Node<T> node = new Node<>(data);
        if (isEmpty()) {
            head = node;
            tail = node;
        } else {
            node.next = head;
            head.pre = node;
            head = node;
        }
        length++;
    }

    /**
     * '尾结点插入法
     *
     * @param data
     */
    void addData(T data) {
        Node<T> node = new Node<>(data);
        if (isEmpty()) {
            head = node;
            tail = node;
        } else {
            tail.next = node;
            node.pre = tail;
            tail = node;
        }
        length++;
    }

    int length() {
        return length;
    }

    T get(int index) {
        Node cur = this.head;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        return (T) cur.data;
    }

    void add(int index, T data) {
        if (index == 0) {
            addFirst(data);
            return;
        } else if (index == length) {
            addData(data);
            return;
        }
        Node cur = this.head;
        for (int i = 0; i < index - 1; i++) {
            cur = cur.next;
        }
        Node pre = cur;
        Node<T> node = new Node<>(data);
        node.next = pre.next;
        pre.next.pre = node;
        pre.next = node;
        node.pre = pre;
        length++;
    }

    void deleteFirst() {
        if (isEmpty()) {
            return;
        }
        if (length == 1) {
            head = null;
            tail = head;
        } else {
            head.next.pre = null;
            head = head.next;
        }
        length--;
    }

    void deleteLast() {
        if (isEmpty()) {
            return;
        }
        if (length == 1) {
            head = null;
            tail = null;
        } else {
            tail.pre.next = null;
            tail = tail.pre;
        }
        length--;
    }

    void delete(int index) {
        if (index < 0) {
            return;
        }
        if (index == 0) {
            deleteFirst();
            return;
        } else if (index == length) {
            deleteLast();
            return;
        }
        Node pre = this.head;
        for (int i = 0; i < index - 1; i++) {
            pre = pre.next;
        }
        pre.next.next.pre = pre;
        pre.next = pre.next.next;
        length--;
    }

    void set(int index, T data) {
        if (head == null) {
            return;
        }
        Node cur = this.head;
        for (int i = 0; i < index - 1; i++) {
            cur = cur.next;
        }
        cur.data = data;
    }

    String traverse() {
        Node cur = this.head;
        StringBuilder sb = new StringBuilder();
        while (cur != null) {
            sb.append(cur.data + "\t");
            cur = cur.next;
        }
        return sb.toString();
    }

    String reverse() {
        Node cur = this.tail;
        StringBuilder sb = new StringBuilder();
        while (cur != null) {
            sb.append(cur.data + "\t");
            cur = cur.pre;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        MyLinkList<Integer> list = new MyLinkList<Integer>();
        list.addData(66);
        list.addFirst(55);
        list.add(1, 101);
        list.addData(-22);
        list.addData(555);
        list.addFirst(9999);
        System.out.println(list.traverse() + " | " + list.reverse());
        list.deleteFirst();
        System.out.println(list.traverse() + "|" + list.reverse());
        list.delete(1);
        System.out.println(list.traverse() + "|" + list.reverse());
        list.delete(1);
        System.out.println(list.traverse() + " " + list.length());
        list.deleteLast();
        System.out.println(list.traverse() + " " + list.length());
        list.deleteLast();
        System.out.println(list.traverse() + " " + list.length());
        list.deleteLast();
        System.out.println(list.traverse() + " " + list.length());
        System.out.println(list.isEmpty());
        list.deleteLast();
    }

}

