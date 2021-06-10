package com.test.algorithm;

import com.test.algorithm.linknode.ListNode;
import com.test.algorithm.tree.TreeNode;

import java.util.*;

/**
 * @Author: movie
 * @Date: 2020/4/7 10:34
 */
public class QueueTest {
    public static void main(String[] args) {
        PriorityQueue<Integer> min = new PriorityQueue<>();
        min.offer(1);
        min.offer(4);
        min.offer(2);
        min.offer(3);
        min.offer(5);
        Iterator<Integer> iterator = min.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        System.out.println(min.peek());
        min.poll();
        System.out.println(min.peek());
        byte a = 127;
        byte b = 127;
        System.out.println(a += b);
        HashMap<Object, Object> map = new HashMap<>();
        String key = "帅丙";
        System.out.println(key.hashCode());
        System.out.println(3 | 9);
        System.out.println(fn(5));
        System.out.println(Integer.toBinaryString(1));
        System.out.println(Integer.numberOfLeadingZeros(2));
        System.out.println(Integer.numberOfTrailingZeros(2));
        System.out.println(Integer.numberOfLeadingZeros(4));
        System.out.println(Integer.numberOfTrailingZeros(4));
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("aaa");
        stringBuilder.append("bb");
        stringBuilder.setLength(4);
        stringBuilder.append("cc");
        System.out.println(stringBuilder.toString());
        new QueueTest().show();
    }


    private PriorityQueue<Integer> max = new PriorityQueue<Integer>(11, new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2.compareTo(o1);
        }
    });

    public ArrayList<Integer> GetLeastNumbers_Solution(int[] input, int k) {
        ArrayList<Integer> integers = new ArrayList<>();
        if (input == null || input.length < 1) {
            return new ArrayList<>();
        }
        for (int i = 0; i < input.length; i++) {
            if (max.size() != k) {
                max.offer(input[i]);
            } else if (max.peek() < input[i]) {
                max.poll();
                max.offer(input[i]);
            }
        }
        integers.addAll(max);
        return integers;
    }

    public static int fn(int n) {
        if (n <= 2) {
            return n;
        }
        //dp[n] = dp[n-1] + dp[n-2]
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    public static int uniquePaths(int m, int n) {
        //dp[i][j] = dp[i-1][j] + dp[i][j-1]
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            dp[m][0] = 1;
        }
        for (int i = 0; i < n; i++) {
//            dp[]
        }
        return 0;
    }

    public double myPow(double x, int n) {
//        if (x == 0) {
//            return 0;
//        }
//        if (n == 0) {
//            return 1;
//        } else if (n < 0) {
//            return 1 / (x * myPow(x, -n - 1));
//        } else if ((n & 1) == 1) {
//            return x * myPow(x, n - 1);
//        } else {
//            return myPow(x * x, 2 / n);
//        }
        if (x == 0) {
            return 0;
        }
        if (n == 0) {
            return 1;
        }
        long b = n;
        if (b < 0) {
            x = 1 / x;
            b = -b;
        }
        double res = 1.0;
        while (b != 0) {
            if ((b & 1) == 1) {
                res *= x;
            }
            b >>= 1;
            x *= x;
        }
        return res;
    }

    public int minArray(int[] numbers) {
        //旋转数组
        if (numbers == null || numbers.length == 0) {
            return Integer.MAX_VALUE;
        }
        int i = 0;
        int j = numbers.length - 1;
        while (i <= j) {
            int mid = i + (j - i) / 2;
            if (numbers[mid] > numbers[i]) {
                i = mid + 1;
            } else if (numbers[mid] < numbers[j]) {
                j = mid;
            } else {
                j--;
            }
        }
        return numbers[i];
    }

    int midArray1(int[] numbers) {
        int i = 0;
        int j = numbers.length - 1;
        int min = Integer.MAX_VALUE;
        while (i <= j) {
            min = Integer.min(min, Integer.min(numbers[i], numbers[j]));
            i++;
            j--;
        }
        return min;
    }

    public String reverseParentheses(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        Stack<Integer> stack = new Stack<>();
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '(') {
                stack.push(i);
            }
            if (chars[i] == ')') {
                Integer start = stack.pop();
                chars[start] = ' ';
                chars[i] = ' ';
                int m = start + 1;
                int n = i - 1;
                while (m < n) {
                    char temp = chars[m];
                    chars[m] = chars[n];
                    chars[n] = temp;
                    m++;
                    n--;
                }
            }
        }
        return new String(chars).replaceAll(" ", "");
    }

    String reverse(char[] chars) {
        int i = 0;
        int j = chars.length - 1;
        while (i < j) {
            char temp = chars[i];
            chars[i] = chars[j];
            chars[j] = temp;
        }
        return new String(chars);
    }

    public int totalHammingDistance(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int ans = 0;
        for (int i = 0; i < 30; i++) {
            int c = 0;
            for (int num : nums) {
                c += (num >> i) & 1;
            }
            ans += c * (nums.length - c);
        }
        return ans;
    }

    public int hammingDistance(int x, int y) {
        int ans = 0;
        for (int i = 0; i < 31; i++) {
            if ((x >> i) != (y >> i)) {
                ans++;
            }
        }
        return ans;
    }

    int numOfOne(int num) {
        int cnt = 0;
        while (num != 0) {
            if ((num & 1) == 1) {
                cnt++;
            }
            num = num >> 1;
        }
        return cnt;
    }

    public int hammingDistance1(int x, int y) {
        return numOfOne1(x ^ y);
    }

    int numOfOne1(int num) {
        int cnt = 0;
        while (num != 0) {
            num = num & num - 1;
            cnt++;
        }
        return cnt;
    }

    String reverseParentheses1(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        Stack<String> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '（') {
                stack.push(sb.toString());
                sb.setLength(0);
            } else if (s.charAt(i) == ')') {
                sb.reverse();
                sb.insert(0, stack.pop());
            } else {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }

    public boolean isPowerOfTwo(int n) {
        return n > 0 && ((n & n - 1) == 0);
    }

    public boolean isPowerOfFour(int n) {
        if (n == 1) {
            return true;
        }
        if (n % 4 != 0) {
            return false;
        }
        return isPowerOfFour(n / 4);
    }

    public boolean[] canEat(int[] candiesCount, int[][] queries) {
        boolean[] answer = new boolean[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int sum = 0;
            int index = 0;
            for (int j = 0; j <= queries[i][0]; j++) {
                sum += candiesCount[i];
            }

            int min = 0;
            int max = 0;
            int idx = 0;
            for (int k = 0; k < queries[i][1]; k++) {
                if (candiesCount[k] - queries[i][2] < 0) {
                    idx++;
                }
            }

            if (sum >= queries[i][1]) {
                answer[i] = true;
            }
        }
        return new boolean[]{};
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) {
            return null;
        }
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
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

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        boolean flag = Math.abs(left - right) <= 1;
        return flag && isBalanced(root.left) && isBalanced(root.right);
    }

    int sum = 0;

    public int findTilt(TreeNode root) {
        if (root == null) {
            return 0;
        }
        dfs(root);
        return ans;
    }


    public int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = dfs(root.left);
        int right = dfs(root.right);
        sum += Math.abs(left - right);
        return left + right + root.val;
    }

    int ans = Integer.MAX_VALUE;

    public int minDiffInBST(TreeNode root) {
        if (root == null) {
            return 0;
        }
        dfs1(root);
        return min;
    }

    int pre = -1;
    int min = Integer.MAX_VALUE;

    void dfs1(TreeNode root) {
        if (root == null) {
            return;
        }
        dfs1(root.left);
        if (pre == -1) {
            pre = root.val;
        } else {
            min = Math.min(min, root.val - pre);
            pre = root.val;
        }
        dfs1(root.right);
    }


    List<Integer> list = new ArrayList<>();

    void inOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        inOrder(root.left);
        list.add(root.val);
        inOrder(root.right);
    }

    public boolean checkSubarraySum(int[] nums, int k) {

        if (nums == null || nums.length == 0) {
            return false;
        }
        int[] dp = new int[nums.length + 1];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = dp[i - 1] + nums[i];
        }
        HashSet<Integer> set = new HashSet<>();
        for (int i = 2; i <= nums.length; i++) {
            set.add(dp[i - 2] % k);
            if (set.contains(dp[i] % k)) {
                return true;
            }
        }
        return false;
    }

    public int tribonacci(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 1;
        }
        return tribonacci(n - 1) + tribonacci(n - 2) + tribonacci(n - 3);
    }

    public static void main1(String[] args) {
        Scanner scan = new Scanner(System.in);
        String str = scan.nextLine();
        if (str == null || str.length() == 0) {
            return;
        }
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            map.put(str.charAt(i), map.getOrDefault(str.charAt(i), 0) + 1);
        }
        int max = -1;
        Map.Entry e = null;
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() > max) {
                e = entry;
                max = entry.getValue();
            }
        }
        System.out.println(e.getKey());
        System.out.println(e.getValue());
        scan.close();
    }

    public int findMaxLength(int[] nums) {
        if (nums == null | nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int[] sum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            sum[i] = sum[i - 1] + (nums[i - 1] == 1 ? 1 : -1);
        }
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 0);
        int ans = 0;
        for (int i = 2; i < n; i++) {
            if (!map.containsKey(sum[i - 2])) {
                map.put(sum[i - 2], i - 2);
            }
            if (map.containsKey(sum[i])) {
                ans = Math.max(ans, i - map.get(sum[i]));
            }
        }
        return ans;
    }

    public int findMaxLength1(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            nums[i] = nums[i] == 1 ? 1 : -1;
        }
        int[] preSum = new int[nums.length];
        preSum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            preSum[i] = preSum[i - 1] + nums[i];
        }
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            if (!map.containsKey(preSum[i])) {
                map.put(preSum[i], i);
            } else {
                ans = Math.max(ans, i - map.get(preSum[i]));
            }
        }
        return ans;
    }

    public boolean checkSubarraySum1(int[] nums, int k) {
        for (int i = 0; i < nums.length; i++) {
            nums[i] = nums[i] == 1 ? 1 : -1;
        }
        int[] preSum = new int[nums.length];
        preSum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            preSum[i] = preSum[i - 1] + nums[i];
        }
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        for (int i = 0; i < nums.length; i++) {
            if (!map.containsKey(preSum[i] % k)) {
                map.put(preSum[i], i);
            } else {
                if (i - map.get(preSum[i]) >= 2) {
                    return true;
                }
            }
        }
        return false;
    }

    public int subsetXORSum(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        dfs(nums, 0, 0);
        return res;
    }

    int res = 0;

    void dfs(int[] nums, int start, int temp) {
        res += temp;
        for (int i = start; i < nums.length; i++) {
            dfs(nums, i + 1, temp ^ nums[i]);
        }
    }

    public int[] divingBoard(int shorter, int longer, int k) {
        if (k == 0) {
            return new int[]{};
        }
        if (shorter == longer) {
            return new int[]{shorter * k};
        }
        int[] res = new int[k + 1];
        for (int i = 0; i <= k; i++) {
            res[i] = longer * k + shorter * (k - i);
        }
        return res;
    }


    public void hanota(List<Integer> A, List<Integer> B, List<Integer> C) {
        C = new ArrayList<>(A.size());
        for (Integer n : A) {
            C.add(n);
        }
    }


    List<TreeNode> inOrdList = new ArrayList<>();

    void midOrd(TreeNode root) {
        if (root == null) {
            return;
        }
        midOrd(root.left);
        inOrdList.add(new TreeNode(root.val));
        midOrd(root.right);
    }

    TreeNode convertBiNode(TreeNode root) {
        if (root == null) {
            return null;
        }
        midOrd(root);
        for (int i = 0; i < inOrdList.size() - 1; i++) {
            inOrdList.get(i).left = null;
            inOrdList.get(i).right = inOrdList.get(i + 1);
        }
        return inOrdList.get(0);
    }


    TreeNode convertBiNode2(TreeNode root) {
        convertBiNodeHelper(root);
        return dummy.right;
    }

    TreeNode dummy = new TreeNode(-1);
    TreeNode prefix = dummy.right;

    void convertBiNodeHelper(TreeNode root) {
        if (root == null) {
            return;
        }
        convertBiNodeHelper(root.left);
        if (prefix == null) {
            dummy.right = root;
            prefix = root;
        } else {
            prefix.right = root;
            prefix = root;
        }
        root.left = null;
        convertBiNodeHelper(root.right);
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        return null;
    }

    public int findMaxForm(String[] strs, int m, int n) {
        int[][] temp = new int[strs.length][2];
        for (int i = 0; i < strs.length; i++) {
            int[] res = sumStr(strs[i]);
            temp[i][0] = res[0];
            temp[i][1] = res[1];
        }
        int[][] dp = new int[m + 1][n + 1];
        for (int k = 0; k < strs.length; k++) {
            int zero = temp[k][0];
            int one = temp[k][1];
            for (int i = m; i >= 0; i--) {
                for (int j = n; j >= 0; j--) {
                    dp[i][j] = Math.max(dp[i - 1][j - 1], dp[i - zero][j - one] + 1);
                }
            }
        }
        return dp[m][n];
    }

    int[] sumStr(String str) {
        int[] res = new int[2];
        int cnt = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '1') {
                cnt++;
            }
        }
        res[1] = cnt;
        res[0] = str.length() - cnt;
        return res;
    }


    public ListNode removeElements(ListNode head, int val) {
        while (head != null && head.val == val) {
            head = head.next;
        }
        if (head == null) {
            return null;
        }
        ListNode cur = head;
        while (cur.next != null) {
            if (cur.val == val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return head;
    }

    public ListNode removeElements1(ListNode head, int val) {
        if (head == null) {
            return null;
        }
        head.next = removeElements(head.next, val);
        return head.val == val ? head.next : head;
    }

    void show() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j <= i; j++) {
                System.out.print(j + "*" + i + "=" + j * i + "\t");
            }
            System.out.println();
        }
    }

    int result = Integer.MAX_VALUE;

    public int getMinimumDifference(TreeNode root) {
        getMinimumDifferenceHelper(root);
        return result;
    }

    TreeNode ppre = null;

    void getMinimumDifferenceHelper(TreeNode root) {
        if (root == null) {
            return;
        }
        getMinimumDifferenceHelper(root.left);
        if (ppre == null) {
            ppre = root;
        } else {
            result = Math.min(result, Math.abs(root.val - ppre.val));
        }
        getMinimumDifferenceHelper(root.right);
    }



}