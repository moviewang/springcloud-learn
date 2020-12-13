package com.test.algorithm.tree;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: movie
 * @Date: 2020/4/30 22:11
 */
public class Solution {
    public static void main(String[] args) {

    }

    List<TreeNode> nodes = new ArrayList<>();

    public void inOrder(TreeNode pRootOfTree) {
        if (pRootOfTree != null) {
            inOrder(pRootOfTree.left);
            nodes.add(pRootOfTree);
            inOrder(pRootOfTree.right);
        }
    }

    public TreeNode Convert(TreeNode pRootOfTree) {
        inOrder(pRootOfTree);
        for (int i = 0; i < nodes.size() - 1; i++) {
            nodes.get(i).right = nodes.get(i + 1);
            nodes.get(i + 1).left = nodes.get(i);
        }
        return nodes.size() > 0 ? nodes.get(0) : null;
    }

    TreeNode pre = null;
    TreeNode root = null;

    public TreeNode Convert1(TreeNode pRootOfTree) {
        if (pRootOfTree == null) {
            return null;
        }
        Convert(pRootOfTree.left);
        if (root == null) {
            root = pRootOfTree;
        }
        if (pre != null) {
            pRootOfTree.left = pre;
            pre.right = pRootOfTree;
        }
        pre = pRootOfTree;
        Convert(pRootOfTree.right);
        return root;
    }

    public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        if (pre == null || pre.length < 1 || in == null || in.length < 1) {
            return null;
        }
        TreeNode root = new TreeNode(pre[0]);
        for (int i = 0; i < in.length; i++) {
            if (in[i] == root.val) {
                root.left = reConstructBinaryTree(Arrays.copyOfRange(pre, 1, i + 1), Arrays.copyOfRange(in, 0, i + 1));
                root.right = reConstructBinaryTree(Arrays.copyOfRange(pre, i + 1, pre.length), Arrays.copyOfRange(in, i + 1, in.length));
            }
        }
        return root;
    }

    public void Mirror(TreeNode root) {
        if (root == null) {
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }

    }

    public void Mirror1(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        Mirror(root.left);
        Mirror(root.right);
    }

    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            list.add(node.val);
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        return list;
    }

    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {

        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        Stack<TreeNode> queue = new Stack<>();
        Stack<ArrayList<Integer>> paths = new Stack<>();
        queue.push(root);
        ArrayList<Integer> vals = new ArrayList<>();
        vals.add(root.val);
        paths.push(vals);
        while (!queue.isEmpty()) {
            TreeNode node = queue.pop();
            ArrayList<Integer> path = paths.pop();
            if (node.left == null && node.right == null && sum(path) == target) {
                list.add(path);
            }
            if (node.left != null) {
                queue.push(node.left);
                ArrayList<Integer> integers = new ArrayList<>(paths.pop());
                integers.add(node.left.val);
                paths.push(integers);
            }
            if (node.right != null) {
                queue.push(node.right);
                ArrayList<Integer> integers = new ArrayList<>(paths.pop());
                integers.add(node.right.val);
                paths.add(vals);
            }
        }
        return list;
    }

    public List<String> binaryTreePaths(TreeNode root) {
        if (root == null) {
            return null;
        }
        Stack<TreeNode> queue = new Stack<>();
        Stack<String> paths = new Stack<>();
        queue.push(root);
        paths.push(root.val + "");
        List<String> list = new ArrayList<>();
        while (!queue.isEmpty()) {
            TreeNode node = queue.pop();
            String path = paths.pop();
            if (node.left == null && node.right == null) {
                list.add(path);
            }
            if (node.left != null) {
                queue.push(node.left);
                paths.push(path + "->" + node.left.val);
            }
            if (node.right != null) {
                queue.push(node.right);
                paths.push(path + "->" + node.right.val);
            }
        }
        return list;
    }

    public int sum(List<Integer> list) {
        return list.stream().reduce(0, (s1, s2) -> s1 + s2);
    }

    public List<String> binaryTreePaths1(TreeNode root) {
        List<String> paths = new LinkedList<>();
        String path = "";
        constructPaths(root, path, paths);
        return paths;
    }

    public void constructPaths(TreeNode root, String path, List<String> paths) {
        if (root == null) {
            return;
        }
        path += root.val;
        if (root.left == null && root.right == null) {
            paths.add(path);
        }
        if (root != null) {
            path += "->";
            constructPaths(root.left, path, paths);
            constructPaths(root.right, path, paths);
        }
    }

    public void constructPaths1(TreeNode root, ArrayList<Integer> path, List<ArrayList<Integer>> paths, int target) {
        if (root == null) {
            return;
        }
        path.add(root.val);
        if (root.left == null && root.right == null && sum(path) == target) {
            paths.add(path);
        }
        if (root.left != null) {
            ArrayList<Integer> integers = new ArrayList<>(path);
            constructPaths1(root.left, integers, paths, target);
        }
        if (root.right != null) {
            ArrayList<Integer> integers = new ArrayList<>(path);
            constructPaths1(root.right, integers, paths, target);
        }
    }

    public int TreeDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = TreeDepth(root.left);
        int right = TreeDepth(root.right);
        return Math.max(right, left) + 1;
    }

    public int TreeDepth1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        ArrayList<TreeNode> level = new ArrayList<>();
        level.add(root);
        int count = 0;
        while (!level.isEmpty()) {
            count++;
            ArrayList<TreeNode> newLevel = new ArrayList<>();
            for (TreeNode treeNode : level) {
                if (treeNode.left != null) {
                    newLevel.add(treeNode.left);
                }
                if (treeNode.right != null) {
                    newLevel.add(treeNode.right);
                }
            }
            level = newLevel;
        }
        return count;
    }

    public boolean IsBalanced_Solution(TreeNode root) {
        if (root == null) {
            return false;
        }
        int left = TreeDepth(root.left);
        int right = TreeDepth(root.left);
        return Math.abs(left - right) == 1;
    }

    List<TreeLinkNode> linkNodes = new ArrayList<>();

    public void inOrderLinkNode(TreeLinkNode pNode) {
        if (pNode != null) {
            inOrderLinkNode(pNode.left);
            linkNodes.add(pNode);
            inOrderLinkNode(pNode.right);
        }
    }

    public TreeLinkNode GetNext(TreeLinkNode pNode) {
        if (pNode == null) {
            return null;
        }
        TreeLinkNode cur = pNode;
        while (cur.next != null) {
            cur = cur.next;
        }
        inOrderLinkNode(cur);
        for (int i = 0; i < linkNodes.size(); i++) {
            if (pNode == linkNodes.get(i)) {
                return i == linkNodes.size() - 1 ? null : linkNodes.get(i + 1);
            }
        }
        return null;
    }

    public boolean isSymmetrical(TreeNode pRoot) {
        return pRoot == null || judge(pRoot.left, pRoot.right);
    }

    public boolean judge(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        } else if (left == null || right == null) {
            return false;
        }
        if (left.val != right.val) {
            return false;
        }
        return judge(left.left, right.right) && judge(left.right, right.left);
    }

    public ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if (pRoot == null) {
            return result;
        }
        ArrayList<TreeNode> level = new ArrayList<>();
        level.add(pRoot);
        int count = 0;
        while (!level.isEmpty()) {
            count++;
            ArrayList<TreeNode> newLevel = new ArrayList<>();
            for (int i = 0; i < level.size(); i++) {
                if (level.get(i).left != null) {
                    newLevel.add(level.get(i).left);
                }
                TreeNode right = level.get(i).right;
                if (right != null) {
                    newLevel.add(right);
                }
            }
            List<Integer> collect = null;
            if ((count & 1) == 1) {
                collect = level.stream()
                        .map(n -> n.val)
                        .collect(Collectors.toList());
            } else {
                collect = level.stream()
                        .map(n -> n.val)
                        .sorted(Comparator.reverseOrder())
                        .collect(Collectors.toList());
            }
            result.add(new ArrayList<>(collect));
            level = newLevel;
        }
        return result;
    }

    ArrayList<ArrayList<Integer>> Print1(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if (pRoot == null) {
            return result;
        }
        ArrayList<TreeNode> level = new ArrayList<>();
        level.add(pRoot);
        while (!level.isEmpty()) {
            ArrayList<TreeNode> newLevel = new ArrayList<>();
            for (int i = 0; i < level.size(); i++) {
                TreeNode left = level.get(i).left;
                if (left != null) {
                    newLevel.add(left);
                }
                TreeNode right = level.get(i).right;
                if (right != null) {
                    newLevel.add(right);
                }
            }
            result.add(((ArrayList) level.stream().map(t -> t.val).collect(Collectors.toList())));
            level = newLevel;
        }
        return result;
    }

    String Serialize(TreeNode root) {
        if (root == null) {
            return "#";
        }
        return root.val + "," + Serialize(root.left) + "," + Serialize(root.right);
    }

    int index = -1;

    TreeNode Deserialize(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        index++;
        String[] nodes = str.split(",");
        if (index > nodes.length) {
            return null;
        }
        TreeNode treeNode = null;
        if (!nodes[index].equals("#")) {
            treeNode = new TreeNode(Integer.valueOf(nodes[index]));
            treeNode.left = Deserialize(str);
            treeNode.right = Deserialize(str);
        }
        return treeNode;
    }

    public boolean HasSubtree(TreeNode root1, TreeNode root2) {
        if (root1 == null || root2 == null) {
            return false;
        }
        return recur(root1, root2) || HasSubtree(root1.left, root2) || HasSubtree(root1.right, root2);
    }

    public boolean recur(TreeNode treeNode1, TreeNode treeNode2) {
        if (treeNode2 == null) {
            return true;
        }
        if (treeNode1 == null || treeNode1.val != treeNode2.val) {
            return false;
        }
        return recur(treeNode1.left, treeNode2.left) && recur(treeNode1.right, treeNode2.right);
    }

    public boolean VerifySquenceOfBST(int[] sequence) {
        if (sequence == null || sequence.length == 0) {
            return false;
        }
        return helpVerify(sequence, 0, sequence.length - 1);
    }

    public boolean helpVerify(int[] sequence, int start, int end) {
        if (start >= end) {
            return true;
        }
        int root = sequence[end];
        int i = start;
        while (i < end && sequence[i] < root) {
            i++;
        }
        for (int j = i; j <= end; j++) {
            if (sequence[j] < root) {
                return false;
            }
        }
        return helpVerify(sequence, start, i - 1) && helpVerify(sequence, i, end - 1);
    }


    PriorityQueue<TreeNode> min = new PriorityQueue<>(Comparator.comparing(t -> t.val));

    TreeNode KthNode(TreeNode pRoot, int k) {
        if (pRoot == null || k < 1) {
            return null;
        }
        helpKthNode(pRoot);
        if (min.isEmpty()) {
            return null;
        }
        int cnt = 0;
        while (cnt++ < k && !min.isEmpty()) {
            min.poll();
        }
        return min.peek();
    }

    public void helpKthNode(TreeNode pRoot) {
        if (pRoot != null) {
            helpKthNode(pRoot.left);
            min.offer(pRoot);
            helpKthNode(pRoot.right);
        }
    }

    List<Integer> result = new ArrayList<>();

    public List<Integer> preorderTraversal(TreeNode root) {
        if (root == null) {
            return result;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            result.add(node.val);
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        return result;
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        if (root != null) {
            inorderTraversal(root.left);
            result.add(root.val);
            inorderTraversal(root.right);
        }
        return result;
    }

    public List<Integer> inorderTraversal1(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (!stack.isEmpty() && cur != null) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            result.add(cur.val);
            cur = cur.right;
        }
        return result;
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        if (root == null) {
            return result;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            result.add(0, node.val);
            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }
        }
        return result;
    }


    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return false;
        }
        int left = helpIsBalanced(root.left);
        int right = helpIsBalanced(root.right);
        if (Math.abs(left - right) > 1) {
            return false;
        }
        return isBalanced(root.left) && isBalanced(root.right);
    }


    public int helpIsBalanced(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = helpIsBalanced(root.left);
        int right = helpIsBalanced(root.right);
        return Math.max(left, right) + 1;
    }

    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (root.left != null && root.left.val >= root.val) {
            return false;
        }
        if (root.right != null && root.right.val <= root.val) {
            return false;
        }
        return isValidBST(root.left) && isValidBST(root.right);
    }

    public boolean isValidBST1(TreeNode root) {
        inOrder(root);
        for (int i = 0; i < nodes.size() - 1; i++) {
            if (nodes.get(i).val >= nodes.get(i + 1).val) {
                return false;
            }
        }
        return true;
    }


}