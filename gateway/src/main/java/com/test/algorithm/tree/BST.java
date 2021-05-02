package com.test.algorithm.tree;

import java.util.*;

/**
 * @Author: movie
 * @Date: 2021/4/2 15:01
 */
public class BST {
    boolean isValidBST(TreeNode root) {
        return isValidBSTHelper(root, null, null);

    }

    boolean isValidBSTHelper(TreeNode root, TreeNode min, TreeNode max) {
        if (root == null) {
            return true;
        }
        if (min != null && min.val >= root.val) {
            return false;
        }

        if (max != null && max.val <= root.val) {
            return false;
        }
        return isValidBSTHelper(root.left, min, root) && isValidBSTHelper(root.right, root, max);
    }

    boolean isInBST(TreeNode root, int target) {
        if (root == null) {
            return false;
        }
        if (root.val == target) {
            return true;
        }
        return isInBST(root.left, target) || isInBST(root.right, target);
    }

    boolean isInBST1(TreeNode root, int target) {
        if (root == null) {
            return false;
        }
        if (root.val == target) {
            return true;
        } else if (root.val < target) {
            return isInBST1(root.right, target);
        } else {
            return isInBST1(root.left, target);
        }
    }

    TreeNode insert(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        if (root.val == val) {
            //
        }
        if (root.val < val) {
            root.right = insert(root.right, val);
        }

        if (root.val > val) {
            root.left = insert(root.left, val);
        }
        return root;
    }

    TreeNode delete(TreeNode root, int val) {
        //root
        if (root == null) {
            return null;
        }

        if (root.val == val) {
            if (root.left == null) {
                return root.right;
            }
            if (root.right == null) {
                return root.left;
            }
            TreeNode min = min(root.right);
            root.val = min.val;
            root.right = delete(root.right, min.val);
        }

        if (root.val < val) {
            root.right = delete(root.right, val);
        }

        if (root.val > val) {
            root.left = delete(root.left, val);
        }
        return root;
    }

    private TreeNode min(TreeNode root) {
        if (root == null) {
            return null;
        }
        while (root.left != null) {
            root = root.left;
        }
        return root;
    }


    boolean isBanlanced(TreeNode root) {
        //root
        if (root == null) {
            return true;
        }
        int left = depth(root.left);
        int right = depth(root.right);
        if (Math.abs(left - right) < 2) {
            return isBanlanced(root.left) && isBanlanced(root.right);
        }
        return false;
    }

    int depth(TreeNode root) {
        //root
        if (root == null) {
            return 0;
        }
        int left = depth(root.left);
        int right = depth(root.right);
        return Math.max(left, right) + 1;
    }

    boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isSymmetricHelp(root.left, root.right);
    }

    boolean isSymmetricHelp(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null || left.val != right.val) {
            return false;
        }
        return isSymmetricHelp(left.left, right.right) && isSymmetricHelp(left.right, right.left);
    }

    public TreeNode mirrorOfTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode temp = root.left;
        root.left = mirrorOfTree(root.right);
        root.right = mirrorOfTree(temp);
        return root;
    }

    Map<Integer, Integer> map = new HashMap<>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int length = inorder.length;
        if (preorder == null || inorder == null || preorder.length < 1 || length < 1) {
            return null;
        }
        for (int i = 0; i < length; i++) {
            map.put(inorder[i], i);
        }
        return buildTreeHelp(preorder, inorder, 0, length - 1, 0, length - 1);
    }

    public TreeNode buildTreeHelp(int[] preorder, int[] inorder, int preleft, int preright, int inleft, int inright) {
        if (preleft > preright) {
            return null;
        }
        int preroot = preleft;
        Integer inorderIndex = map.get(preorder[preroot]);
        TreeNode root = new TreeNode(preorder[preroot]);
        int leftLength = inorderIndex - inleft;
        root.left = buildTreeHelp(preorder, inorder, preleft + 1, preleft + leftLength, inleft, inorderIndex - 1);
        root.right = buildTreeHelp(preorder, inorder, preleft + leftLength + 1, preright, inorderIndex + 1, inright - 1);
        return root;
    }

    public TreeNode mergeTree(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return null;
        }
        if (root1 == null) {
            return root2;
        }
        if (root2 == null) {
            return root1;
        }
        TreeNode newRoot = new TreeNode(root1.val + root2.val);
        newRoot.left = mergeTree(root1.left, root2.left);
        newRoot.right = mergeTree(root1.right, root2.right);
        return newRoot;
    }

    TreeNode mergeTreeBFS(TreeNode root1, TreeNode root2) {
        Queue<TreeNode> queue1 = new LinkedList<>();
        Queue<TreeNode> queue2 = new LinkedList<>();
        if (root1 == null) {
            return root2;
        }
        if (root2 == null) {
            return root1;
        }
        queue1.offer(root1);
        queue2.offer(root2);
        while (!queue1.isEmpty()) {
            TreeNode node1 = queue1.poll();
            TreeNode node2 = queue2.poll();
            node1.val += node2.val;
            if (node1.left == null) {
                node1.left = node2.left;
            } else {
                if (node2.left != null) {
                    queue1.offer(node1.left);
                    queue2.offer(node2.left);
                }
            }

            if (node1.right == null) {
                node1.right = node2.right;
            } else {
                if (node2.right != null) {
                    queue1.offer(node1.right);
                    queue2.offer(node2.right);
                }
            }
        }
        return root1;
    }

    int count(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = count(root.left);
        int right = count(root.right);
        return left + right + 1;
    }

    List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        traverse(root);
        return res;
    }


    Map<String, Integer> memo = new HashMap<>();
    LinkedList<TreeNode> res = new LinkedList<>();

    String traverse(TreeNode root) {
        if (root == null) {
            return "#";
        }
        String left = traverse(root.left);
        String right = traverse(root.right);
        String subTree = left + "," + right + "," + root.val;
        int freq = memo.getOrDefault(subTree, 0);
        if (freq == 1) {
            res.add(root);
        }
        memo.put(subTree, freq + 1);
        return subTree;
    }

    int depth1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int count = 0;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            System.out.println(node.val);
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        return count;
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == q || p == root) {
            return root;
        }
        if (Math.max(p.val, q.val) < root.val) {
            lowestCommonAncestor(root.left, p, q);
        }
        if (Math.min(p.val, q.val) > root.val) {
            lowestCommonAncestor(root.left, p, q);
        }
        return root;
    }

    public boolean verifyPostorder(int[] postorder) {
        return verifyPostorderHelp(postorder, 0, postorder.length - 1);
    }

    public boolean verifyPostorderHelp(int[] postorder, int i, int j) {
        if (postorder == null || i >= j) {
            return true;
        }
        int k = i;
        for (; k < j; k++) {
            if (postorder[i] > postorder[j]) {
                break;
            }
        }
        int m = k;
        for (; k < j; k++) {
            if (postorder[k] < postorder[j]) {
                return false;
            }
        }

        return verifyPostorderHelp(postorder, i, m - 1)
                && verifyPostorderHelp(postorder, m, j - 1);
    }

    boolean verifyPostorder1(int[] postorder) {
        if (postorder == null || postorder.length < 1) {
            return true;
        }
        Stack<Integer> stack = new Stack<>();
        int root = Integer.MAX_VALUE;
        for (int j = postorder.length - 1; j >= 0; j--) {
            if (postorder[j] > root) {
                return false;
            }
            while (!stack.isEmpty() && stack.peek() > postorder[j]) {
                root = stack.pop();
            }
            stack.push(postorder[j]);
        }
        return true;
    }

    int cnt = 0;
    int val;

    public int kthLargest(TreeNode root, int k) {
        kthLargestHelper(root, k);
        return val;
    }

    void kthLargestHelper(TreeNode root, int k) {
        if (root == null) {
            return;
        }
        kthLargestHelper(root.right, k);
        if (cnt++ == k) {
            val = root.val;
        }
        kthLargestHelper(root.left, k);
    }

    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if (A == null || B == null) {
            return false;
        }
        return recur(A, B) || isSubStructure(A.left, B) || isSubStructure(A.right, B);
    }

    boolean recur(TreeNode A, TreeNode B) {
        if (B == null) {
            return true;
        }
        if (A == null || A.val != B.val) {
            return false;
        }
        return recur(A.right, B.right) && recur(A.left, B.left);
    }

    Node head, pre;
    List<Node> nodes = new ArrayList<>();

    public Node treeToDoublyList(Node root) {
        if (root == null) {
            return null;
        }
        int size = nodes.size();
        for (int i = 0; i < size - 1; i++) {
            nodes.get(i).right = nodes.get(i + 1);
        }
        for (int j = size - 1; j > 0; j--) {
            nodes.get(j).left = nodes.get(j - 1);
        }
        nodes.get(0).left = nodes.get(size - 1);
        nodes.get(size - 1).right = nodes.get(0);
        return nodes.get(0);
    }

    void inOrder(Node cur) {
        if (cur == null) {
            return;
        }
        inOrder(cur.left);
        nodes.add(cur);
        inOrder(cur.left);
    }

    void inOrder1(Node root) {
        if (root == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            while (!stack.isEmpty()) {
                root = stack.pop();
//                System.out.println(root.val);
                if (pre == null) {
                    head = root;
                }
                root.left = pre;
                pre.left = root;
                root = root.right;
            }
        }
        head.left = pre;
        pre.right = head;
    }

    public int amount(int[] coins, int money) {
        if (coins == null || coins.length < 1) {
            return -1;
        }
        int[] dp = new int[money + 1];
        dp[0] = 0;
        for (int i = 1; i < money; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= money) {
                    min = Math.min(min, dp[i - coins[j]]) + 1;
                }
            }
            dp[i] = min;
        }
        return dp[money] == Integer.MAX_VALUE ? -1 : dp[money];
    }

    int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    int fib(int n) {
        int[] memo = new int[n + 1];

        return fibHelper(n, memo);
    }

    int fibHelper(int n, int[] memo) {
        if (n < 2) {
            memo[n] = n;
        }
        memo[n] = fibHelper(n - 1, memo) + fibHelper(n - 2, memo);
        return memo[n];
    }

    LinkedList<Integer> path = new LinkedList<>();
    LinkedList<List<Integer>> paths = new LinkedList<>();

    public List<List<Integer>> pathSum(TreeNode root, int target) {
        pathSumHelper(root, target);
        return paths;
    }

    void pathSumHelper(TreeNode root, int target) {
        if (root == null) {
            return;
        }
        path.add(root.val);
        target -= root.val;
        if (root.left == null && root.right == null && target == 0) {
            paths.add(new LinkedList<>(path));
        }
        pathSumHelper(root.left, target);
        pathSumHelper(root.right, target);
        path.removeLast();
    }
}
