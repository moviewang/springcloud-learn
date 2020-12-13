package com.test.algorithm.tree;

import java.util.ArrayList;

/**
 * @Author: movie
 * @Date: 2020/4/30 22:10
 */
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int val) {
        this.val = val;
    }

    public static void main(String[] args) {
        ArrayList<Integer> l1 = new ArrayList<>();
        l1.add(10);
        l1.add(20);
        l1.add(30);
        ArrayList<Integer> l2 = new ArrayList<>();
        l2.add(33);
        l2.add(34);
        l1 = l2;
        l2.forEach(l -> System.out.println(l));
    }

    public int maxDepth(TreeNode root) {

        if (root == null) {
            return 0;
        }
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return left - right > 0 ? left + 1 : right + 1;
    }

    public int maxDepth1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        ArrayList<TreeNode> nodes = new ArrayList<>();
        nodes.add(root);
        int cnt = 0;
        while (!nodes.isEmpty()) {
            ArrayList<TreeNode> temp = new ArrayList<>();
            for (int i = 0; i < nodes.size(); i++) {
                TreeNode node = nodes.get(i);
                temp.add(node.left);
                temp.add(node.right);
            }
            nodes = temp;
            cnt++;
        }
        return cnt;
    }


}
