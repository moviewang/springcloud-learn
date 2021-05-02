package com.test.algorithm.tree;

import java.util.*;
import java.util.stream.Collectors;

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
        StringJoiner stringJoiner = new StringJoiner(",", "[", "]");
        System.out.println(stringJoiner.toString());
        l1.forEach(l -> {
            stringJoiner.add(l + "");
        });
        stringJoiner.add("null");
        System.out.println(stringJoiner.toString());
    }


    /**
     * 广度优先遍历
     *
     * @param root
     * @return
     */
    public TreeNode mirrorTree(TreeNode root) {
        if (root == null) {
            return null;
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
            if (node.left != null) {
                queue.offer(node.right);
            }
        }
        return root;
    }

    public void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode temp = root.left;
        root.left = mirrorTree(root.right);
        root.right = mirrorTree(temp);
    }

    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return recur(root.left, root.right);
    }

    private boolean recur(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null || left.val != right.val) {
            return false;
        }
        return recur(left.left, right.right) && recur(left.right, right.left);
    }

    public boolean isBalanced(TreeNode root) {
        return dfs1(root) != -1;
    }

    //后序遍历
    public int dfs1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = dfs1(root.left);
        if (left == -1) {
            return -1;
        }
        int right = dfs1(root.right);
        if (right == -1) {
            return -1;
        }
        return Math.abs(left - right) < 2 ? Math.max(left, right) + 1 : -1;
    }


    public TreeNode ans;

    public boolean dfs2(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return false;
        }
        boolean lson = dfs2(root.left, p, q);
        boolean rson = dfs2(root.right, p, q);
        if ((lson && rson) || ((root.val == p.val || root.val == q.val) && (lson || rson))) {
            ans = root;
        }
        return lson || rson || (root.val == p.val || root.val == q.val);
    }

    private Set<Integer> visited = new HashSet<>();
    private Map<Integer, TreeNode> parent = new HashMap<>();

    private void dfs3(TreeNode root) {
        if (root.left != null) {
            parent.put(root.left.val, root);
            dfs3(root.left);
        }
        if (root.right != null) {
            parent.put(root.right.val, root);
            dfs3(root.right);
        }
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        dfs3(root);
        while (p != null) {
            visited.add(p.val);
            p = parent.get(p.val);
        }

        while (q != null) {
            if (visited.contains(q.val)) {
                return q;
            }
            q = parent.get(q.val);
        }
        return null;
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

    public List<List<Integer>> levelOrder11(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        ArrayList<List<Integer>> ans = new ArrayList<>();
        ArrayList<TreeNode> nodes = new ArrayList<>();
        nodes.add(root);
        ans.add(List.of(root.val));
        while (!nodes.isEmpty()) {
            ans.add(nodes.stream().map(treeNode -> treeNode.val).collect(Collectors.toList()));
            ArrayList<TreeNode> temp = new ArrayList<>();
            for (TreeNode node : nodes) {
                if (node.left != null) {
                    temp.add(node.left);
                }
                if (node.right != null) {
                    temp.add(node.right);
                }
            }
            nodes = temp;
        }
        return ans;
    }

    public List<List<Integer>> levelOrder1(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        List<List<Integer>> ans = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int cnt = 0;
        while (!queue.isEmpty()) {
//            ArrayList<Integer> level = new ArrayList<>();
            LinkedList<Integer> tmp = new LinkedList<>();
            cnt++;
            for (int size = queue.size(); size > 0; size--) {
                TreeNode node = queue.poll();
                if ((cnt & 1) == 0) {
                    tmp.addLast(node.val);
                } else {
                    tmp.addFirst(node.val);
                }
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            ans.add(tmp);
        }
        return ans;
    }

    int res, cnt;

    private void inOrd(TreeNode root) {
        if (root == null || cnt == 0) {
            return;
        }
        inOrd(root.right);
        if (cnt-- == 0) {
            res = root.val;
            return;
        }
        inOrd(root.left);
    }

    public int kthLargest(TreeNode root, int k) {
        this.cnt = k;
        inOrd(root);
        return res;
    }


    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        if (Math.max(p.val, q.val) < root.val) {
            return lowestCommonAncestor1(root.left, p, q);
        }

        if (Math.min(p.val, q.val) < root.val) {
            return lowestCommonAncestor1(root.right, p, q);
        }
        return root;
    }

    public int[] levelOrder(TreeNode root) {
        if (root == null) {
            return new int[]{};
        }
        Queue<TreeNode> queue = new LinkedList<>();
        ArrayList<Integer> res = new ArrayList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            res.add(node.val);
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        int[] ans = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            ans[i] = res.get(i);
        }
        return ans;
    }

    public boolean verifyPostorder(int[] postorder) {
        return recur(postorder, 0, postorder.length - 1);
    }

    public boolean recur(int[] postorder, int i, int j) {
        if (i >= j) {
            return true;
        }
        int p = i;
        while (postorder[p] < postorder[j]) {
            p++;
        }
        int m = p;
        while (postorder[p] > postorder[j]) {
            p++;
        }
        return p == j && recur(postorder, i, m - 1) && recur(postorder, m, j - 1);
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || preorder.length < 1 || inorder.length < 1) {
            return null;
        }
        //数组长度判空null
        TreeNode root = new TreeNode(preorder[0]);
        for (int i = 0; i < inorder.length; i++) {
            if (preorder[0] == inorder[i]) {
                root.left = buildTree(Arrays.copyOfRange(preorder, 1, i + 1),
                        Arrays.copyOfRange(inorder, 0, i));
                root.right = buildTree(Arrays.copyOfRange(preorder, i + 1, preorder.length),
                        Arrays.copyOfRange(inorder, i + 1, inorder.length));
            }
        }
        return root;
    }

    LinkedList<List<Integer>> result = new LinkedList<>();
    LinkedList<Integer> path = new LinkedList<>();

    public List<List<Integer>> pathSum(TreeNode root, int target) {
        pathSumHelper(root, target);
        return result;
    }

    private void pathSumHelper(TreeNode root, int target) {
        if (root == null) {
            return;
        }
        path.add(root.val);
        target -= root.val;
        if (root.left == null && root.right == null && target == 0) {
            result.add(new LinkedList<>(path));
        }
        pathSumHelper(root.right, target);
        path.removeLast();
    }

    public boolean isSubStructure(TreeNode A, TreeNode B) {
        return (A != null && B != null)
                && (isSubStructureHelp(A, B) || isSubStructure(A.right, B) || isSubStructure(A.left, B));
    }

    private boolean isSubStructureHelp(TreeNode A, TreeNode B) {
        if (B == null) {
            return true;
        }
        if (A == null || A.val != B.val) {
            return false;
        }
        return isSubStructureHelp(A.left, B.left) && isSubStructureHelp(A.right, B.right);
    }

    public Node treeToDoublyList(Node root) {
        inorder(root);
        if (inorderList.isEmpty()) {
            return root;
        }
        if (inorderList.size() == 1) {
            inorderList.get(0).right = inorderList.get(0);
            inorderList.get(0).left = inorderList.get(0);
            return root;
        }
        int size = inorderList.size();
        for (int i = 1; i < size - 1; i++) {
            inorderList.get(i).left = inorderList.get(i - 1);
            inorderList.get(i).right = (inorderList.get(i + 1));

        }
        inorderList.get(0).left = inorderList.get(size - 1);
        inorderList.get(0).right = inorderList.get(size - 1);

        inorderList.get(size - 1).right = inorderList.get(0);
        inorderList.get(size - 1).left = inorderList.get(size - 2);
        return inorderList.get(0);
    }


    Node pre, head;

    public Node treeToDoublyList1(Node root) {
        if (root == null) {
            return null;
        }
        treeToDoublyList1Helper(root);
        pre.right = head;
        head.left = pre;
        return head;
    }

    void treeToDoublyList1Helper(Node cur) {
        if (cur == null) {
            return;
        }
        treeToDoublyList1Helper(cur.left);
        if (pre == null) {
            head = pre;
        } else {
            pre.right = cur;
        }
        cur.left = pre;
        pre = cur;
        treeToDoublyList1Helper(cur.right);
    }


    private List<Node> inorderList = new ArrayList<>();

    public void inorder(Node root) {
        if (root == null) {
            return;
        }
        inorder(root.getLeft());
        inorderList.add(root);
        inorder(root.getRight());
    }

    public String serialize(TreeNode root) {
        StringJoiner joiner = new StringJoiner(",", "[", "]");
        if (root == null) {
            return joiner.toString();
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode treeNode = queue.poll();
            if (treeNode != null) {
                joiner.add(treeNode.val + "");
                queue.add(treeNode.left);
                queue.add(treeNode.right);
            } else {
                joiner.add("null");
            }
        }
        return joiner.toString();
    }

    public TreeNode deserialize(String data) {
        if ("[]".equals(data) || data == null) {
            return null;
        }
        String[] vals = data.substring(1, data.length() - 1).split(",");
        TreeNode root = new TreeNode(Integer.parseInt(vals[0]));
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int i = 1;
        while (!queue.isEmpty()) {
            TreeNode treeNode = queue.poll();
            if (!"null".equals(vals[i])) {
                treeNode.left = new TreeNode(Integer.parseInt(vals[i]));
                queue.add(treeNode.left);
                i++;
            }
            if (!"null".equals(vals[i])) {
                treeNode.right = new TreeNode(Integer.parseInt(vals[i]));
                queue.add(treeNode.right);
                i++;
            }
        }
        return root;
    }

    public TreeNode increasingBST(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode newRoot = new TreeNode(inorderList.get(0).val);
        for (int i = 0; i < inorderList.size() - 1; i++) {
            inorderList.get(i).right = inorderList.get(i + 1);
            inorderList.get(i).left = null;

        }
        return newRoot;
    }

    private TreeNode newRoot;

    public TreeNode increasingBST1(TreeNode root) {
        return increasingBSTHelper(root, null);
    }

    TreeNode increasingBSTHelper(TreeNode root, TreeNode tail) {
        if (root == null) {
            return null;
        }
        TreeNode left = increasingBSTHelper(root.left, tail);
        root.left = null;
        root.right = increasingBSTHelper(root, tail);
        return left;
    }

    public Node treeToDoublyList11(Node root) {
        return treeToDoublyListHelper(root, null);
    }

    Node treeToDoublyListHelper(Node root, Node tail) {
        if (root == null) {
            return tail;
        }
        Node left = treeToDoublyListHelper(root.left, root);
        root.left = left;
        root.right = treeToDoublyListHelper(root.right, tail);
        return left;
    }

}

class Node {
    public Node left;
    public int val;
    public Node right;

    public Node(int val) {
        this.val = val;
    }

    public Node(int val, Node left, Node right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }
}

