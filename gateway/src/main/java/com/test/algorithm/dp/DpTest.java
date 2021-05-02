package com.test.algorithm.dp;

import com.test.algorithm.tree.TreeNode;

import java.util.*;

/**
 * @Author: movie
 * @Date: 2020/5/1 17:40
 */
public class DpTest {

    public static void main(String[] args) {
        int[] nums = {2, 1, 2, 4, 3};
        int[] res = new DpTest().greatNumber(nums);
        System.out.println(Arrays.toString(res));
        int aaa = new DpTest().countSubstrings("annnaa");
        System.out.println(aaa);
        System.out.println(Math.min(Integer.MAX_VALUE, 1));

        String s = "ab-cd";
        System.out.println(new DpTest().reverseString(s));
        System.out.println(Runtime.getRuntime().availableProcessors());
        ArrayList<String> strings = new ArrayList<>();
        strings.add("aaa");
        strings.add("bb");
        strings.add("c");
        String[] array = strings.toArray(new String[0]);
        for (String str : array) {
            System.out.println(str);
        }
    }

    public static int f(int n) {
        if (n < 1) {
            return n;
        }
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i < n; i++) {
            dp[i] = dp[i - 1] + dp[i + 1];
        }
        return dp[n];
    }

    public static int dp1(int m, int n) {
        //dp[i][j] = dp[i-1][j] + dp[i][j-1]
        if (m < 1 || n < 1) {
            return 0;
        }
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }

    public static int unqiuePaths(int m, int n) {
        if (m < 1 || n < 1) {
            return 0;
        }
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[j] = dp[j] + dp[j - 1];
            }
        }
        return dp[n - 1];
    }

    public static int dp2(int[][] arr) {
        //dp[i][j] = min(dp[i-1][j],dp[i][j-1]) + dp[i][j]
        int m = arr.length;
        int n = arr[0].length;
        if (m < 1 || n < 1) {
            return 0;
        }
        int[][] dp = new int[m][n];
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i - 1][0] + arr[i][0];
        }
        for (int i = 1; i < n; i++) {
            dp[0][i] = dp[0][i - 1] + arr[0][i];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[j][i - 1]) + arr[i][j];
            }
        }
        return dp[m - 1][n - 1];
    }

    public static int dp3(String word1, String word2) {
        //dp[i][j] = dp[i-1][j-1] or dp[i][j] = min (dp[i-1][j-1] + 1, dp[i][j-1] + 1, dp[i-1][j] + 1)
        if (word1 == null || word2 == null) {
            return -1;
        }
        int n1 = word1.length();
        int n2 = word2.length();
        int[][] dp = new int[n1 + 1][n2 + 1];
        for (int i = 1; i < n1; i++) {
            dp[i][0] = dp[i - 1][0] + 1;
        }
        for (int i = 1; i < n2; i++) {
            dp[0][i - 1] = dp[0][i - 1] + 1;
        }
        for (int i = 1; i < n1; i++) {
            for (int j = 1; j < n2; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j - 1], dp[i - 1][j]), dp[i][j - 1]) + 1;
                }

            }
        }
        return dp[n1][n2];
    }

    public boolean checkSubarraySum(int[] nums, int k) {
        if (nums == null) {
            return false;
        }
        //dp[i] = dp[i-1] + num[i]  sum = dp[j] -dp[i] + nums[i]
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = dp[i - 1] + nums[i];
        }
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int sum = dp[j] - dp[i] + nums[i];
                if ((sum == 0 && k == 0) || (k != 0 && sum % k == 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    public int rob(int[] nums) {
        //dp[i] = max(nums[i] + dp[i-2], dp[i-1])
        if (nums == null || nums.length < 1) {
            return 0;
        }
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        if (nums.length < 2) {
            return nums[0];
        }
        dp[1] = nums[0] > nums[1] ? nums[0] : nums[1];
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(nums[i] + dp[i - 2], dp[i - 1]);
        }
        return dp[nums.length - 1];
    }


    public int maxSubArray1(int[] nums) {
        //dp[i] = (dp[i-1] * nums[i])
        if (nums == null || nums.length < 1) {
            return 0;
        }
        int[] max = new int[nums.length];
        int[] min = new int[nums.length];
        max[0] = nums[0];
        min[0] = nums[0];
        int maxMulti = max[0];
        for (int i = 1; i < nums.length; i++) {
            max[i] = Math.max(max[i - 1] * nums[i], Math.max(min[i - 1] * nums[i], nums[i]));
            min[i] = Math.min(min[i - 1] * nums[i], Math.min(max[i - 1] * nums[i], nums[i]));
            maxMulti = Math.max(max[i], maxMulti);
        }
        return maxMulti;
    }

    public int numSquares(int n) {
        int max = n + 1;
        int[] dp = new int[n + 1];
        Arrays.fill(dp, max);
        int squareLen = ((int) Math.sqrt(n)) + 1;
        int[] squareArray = new int[squareLen];
        for (int i = 0; i < squareLen; i++) {
            squareArray[i] = i * i;
        }
        dp[0] = 0;
        for (int i = 1; i < n; i++) {
            for (int j = i; j < squareLen; j++) {
                if (i >= squareArray[i]) {
                    dp[i] = Math.min(dp[i], dp[i - squareArray[j]] + 1);
                }
            }
        }
        return dp[n] > n ? -1 : dp[n];
    }


    public String longestPalindrome1(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }
        if (s.length() < 2) {
            return s;
        }
        int maxLen = 1;
        int begin = 0;
        boolean[][] dp = new boolean[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            dp[i][i] = true;
        }
        for (int j = 1; j < s.length(); j++) {
            for (int i = 0; i < j; i++) {
                if (s.charAt(i) != s.charAt(j)) {
                    dp[i][j] = false;
                } else {
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }
                if (dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }

    public String longestPalindrome2(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }
        if (s.length() < 2) {
            return s;
        }
        int maxLen = 0;
        int begin = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                if (isPalindrome(s, i, j) && maxLen < (j - i + 1)) {
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }

    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }
        if (s.length() < 2) {
            return s;
        }
        int maxLen = 1;
        int begin = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                if (isPalindrome(s, i, j) && maxLen > (j - i + 1)) {
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }

    public boolean isPalindrome(String s, int i, int j) {
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length < 1) {
            return 0;
        }
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int ans = 0;
        for (int i = 1; i < nums.length; i++) {
            int maxVal = 0;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    maxVal = Math.max(maxVal, dp[j]) + 1;
                }
            }
            dp[i] = maxVal + 1;
            ans = Math.max(dp[i], ans);
        }
        return ans;
    }

    public int minPathSum(int[][] grid) {
        if (grid == null) {
            return 0;
        }
        //dp[i][j] = min(dp[i][j-1], dp[i-1][j]) + grid[i][j]
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];
        for (int j = 1; j < m; j++) {
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }
        for (int i = 1; i < n; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i][j - 1], dp[i - 1][j]) + grid[i][j];
            }
        }
        return dp[m - 1][n - 1];
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        HashSet<String> set = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i < s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && set.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }

    public int numDecodings(String s) {
        char[] chars = s.toCharArray();
        int[] dp = new int[chars.length + 1];
        dp[0] = 1;
        dp[1] = chars[0] == '0' ? 0 : 1;
        for (int i = 2; i <= chars.length; i++) {
            int n = chars[i - 2] + '0' * 10 + (chars[i - 1] + '0');
            if (chars[i - 1] == '0' && chars[i - 2] == '0') {
                return 0;
            } else if (chars[i - 2] == '0') {
                dp[i] = dp[i - 1];
            } else if (chars[i - 1] == '0') {
                if (n > 26) {
                    return 0;
                }
                dp[i] = dp[i - 2];
            } else if (n > 26) {
                dp[i] = dp[i - 1];
            } else {
                dp[i] = dp[i - 1] + dp[i - 2];
            }
        }
        return dp[chars.length - 1];
    }


    //
    public int maxSubArray3(int[] nums) {
        if (nums == null || nums.length < 1) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int max = Integer.MIN_VALUE;
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum > max) {
                    max = sum;
                }
            }
            res = Math.max(res, max);
        }
        return res;
    }

    public int maxSubArray2(int[] nums) {
        if (nums == null || nums.length < 1) {
            return 0;
        }
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int max = dp[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            max = Math.max(dp[i], max);
        }
        return max;
    }

    public boolean isSubsequence(String s, String t) {
        if (s == null || t == null) {
            return false;
        }
        for (int i = 0; i < s.length(); i++) {
            int index = t.indexOf(s.charAt(i));
            if (index == -1) {
                return false;
            }
            t = t.substring(index + 1);
        }
        return true;
    }

    public boolean isSubsequence1(String s, String t) {
        if (s == null || t == null) {
            return false;
        }
        if (s.length() < 1) {
            return true;
        }
        boolean[] dp = new boolean[s.length()];
        int index = t.indexOf(s.charAt(0));
        dp[0] = index != -1;
        for (int i = 1; i < s.length(); i++) {
            t = t.substring(index + 1);
            index = t.indexOf(s.charAt(i));
            dp[i] = dp[i - 1] && index != -1;
        }
        return dp[s.length() - 1];
    }

    public int waysToStep(int n) {
        //dp[i] ] = dp[i-1] + dp[i-2] + dp[i-3]
        if (n < 3) {
            return n;
        }
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;
        for (int i = 4; i <= n; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2]) % 1000000007 + dp[i - 3];
            dp[i] = dp[i] % 1000000007;
        }
        return dp[n];
    }

    public int massage(int[] nums) {
        //dp[i] = max(dp[i-2] + nums[i], dp[i-1])
        if (nums == null || nums.length < 1) {
            return 0;
        }
        if (nums.length < 2) {
            return nums[0];
        }
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = nums[0] > nums[1] ? nums[0] : nums[1];
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }
        return dp[nums.length - 1];
    }

    public int maxSubArrayAll(int[] nums) {
        if (nums == null || nums.length < 1) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                max = Math.max(sum, max);
            }
            ans = Math.max(ans, max);
        }
        return ans;
    }

    public int minCostClimbingStairs(int[] cost) {
        if (cost == null || cost.length < 1) {
            return 0;
        }
        //dp[i] = min(dp[i-1], ]dp[i-1] + cost[i], dp[i-2] + cost[i])
        if (cost.length == 1) {
            return cost[0];
        }
        if (cost.length < 3) {
            return Math.min(cost[0], cost[1]);
        }
        int[] dp = new int[cost.length];
        dp[0] = 0;
        dp[1] = cost[0];
        for (int i = 2; i < cost.length; i++) {
            dp[i] = Math.min(dp[i - 1] + cost[i], dp[i - 2] + cost[i]);
        }
        return Math.min(dp[cost.length - 1], dp[cost.length - 1]);
    }

    public boolean divisorGame(int N) {
        return (N & 1) == 0;
    }

    public boolean oneEditAway(String first, String second) {
        if (first.equals(second)) {
            return true;
        }
        if (Math.abs(first.length() - second.length()) > 1) {
            return false;
        }
        int i = 0;
        int len1 = first.length();
        int len2 = second.length();
        int j = first.length() - 1;
        int k = second.length() - 1;
        while (i < len1 && i < len2 && first.charAt(i) == second.charAt(i)) {
            i++;
        }
        while (j >= 0 && k >= 0 && first.charAt(j) == second.charAt(k)) {
            j--;
            k--;
        }
        return j - i < 1 && k - i < 1;
    }

    public boolean exist(char[][] board, String word) {
        //dp[i][j] = dp[i-1][j] + board[i][j]
        // or dp[i][j-1] + board[i][j] or
        // dp[i+1][j] + board[i][j]
        // dp[j+1][i] + board[i][j]
        if (board == null) {
            return false;
        }
        int m = board.length;
        int n = board[0].length;
        String[][] dp = new String[m][n];
        dp[0][0] = board[0][0] + "";
        for (int j = 1; j < n; j++) {
            dp[0][j] = dp[0][j - 1] + board[0][j] + "";
            dp[m - 1][j] = dp[m - 1][j - 1] + board[m - 1][j] + "";
        }
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i - 1][0] + board[i][0] + "";
            dp[i][n - 1] = dp[i - 1][n - 1] + board[i][n - 1] + "";
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + board[i][j];
                if (word.equals(dp[i][j])) {
                    return true;
                }
            }
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i][j - 1] + board[i][j];
                if (word.equals(dp[i][j])) {
                    return true;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (word.equals(dp[i + 1][j] + board[i][j])
                        || word.equals(dp[i][j + 1] + board[i][j])
                ) {
                    return true;
                }
            }
        }
        return false;
    }

    private int[] coins = {1, 5, 10, 25};
    private final int mod = 1000000007;

    public int waysToChange(int n) {
        //dp[n] = dp[n] +  dp[n-coin]
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int coin : coins) {
            for (int i = coin; i <= n; i++) {
                dp[i] = (dp[i] + dp[i - coin]) % mod;
            }
        }
        return dp[n];
    }

    public int findLengthOfLCIS(int[] nums) {
        if (nums == null || nums.length < 1) {
            return 0;
        }
        int res = 0;
        int anchor = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i - 1] >= nums[i]) {
                anchor = i;
            }
            res = Math.max(res, i - anchor + 1);
        }
        return res;
    }

    public static Character mostFrequentLetter(String string) {
        if (string == null || string.length() < 1) {
            return null;
        }
        int letterCount = 0;
        TreeMap<Character, Integer> treeMap = new TreeMap<>();
        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            if (Character.isLetter(c)) {
                letterCount++;
                char ch = Character.toLowerCase(c);
                treeMap.put(ch, treeMap.getOrDefault(ch, 0) + 1);
            }
        }
        if (letterCount == 0) {
            return null;
        }
        Integer max = Collections.max(treeMap.values());
        Set<Map.Entry<Character, Integer>> entries = treeMap.entrySet();
        for (Map.Entry<Character, Integer> entry : entries) {
            if (Objects.equals(entry.getValue(), max)) {
                return entry.getKey();
            }
        }
        return null;
    }

    public int uniquePaths(int[][] grid) {
        //dp[i][j] = dp[i-1][j] + dp[i][j-1]
        int m = grid.length;
        int n = grid[0].length;
        if (m < 1 || n < 1) {
            return 0;
        }
        int[][] dp = new int[m][n];
        for (int j = 0; j < n; j++) {
            dp[0][j] = 1;
        }

        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }

    int minSumPaths(int[][] grid) {

        //dp[i][j] = min(dp[i-1][j], dp[i][j-1]) + nums[i][j]
        int m = grid.length;
        int n = grid[0].length;
        if (m < 1 || n < 1) {
            return 0;
        }
        int[][] dp = new int[m][n];
        for (int j = 1; j < n; j++) {
            dp[0][j] = grid[0][j] + dp[0][j - 1];
        }

        for (int i = 1; i < m; i++) {
            dp[i][0] = grid[i][0] + dp[i - 1][0];
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        return dp[m - 1][n - 1];
    }

    public int minDistance(String word1, String word2) {
        //dp[i][j] = min(dp[i-1][j-1], dp[i-1][j], dp[i][j-1]) + 1
        return 0;
    }

    int rob1(int[] nums) {
        //dp[i] = dp[i -2] + nums[i]
        // dp[1] = 2  dp[2] = 7 dp[3] = dp[1] + num[i] dp[4] = dp[2] +
        int len = nums.length;
        if (len < 1) {
            return 0;
        }
        if (len == 1) {
            return nums[0];
        }
        int[] dp = new int[len];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < len; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }
        return dp[len - 1];
    }

    int[] greatNumber(int[] nums) {
        int len = nums.length;
        if (len < 1) {
            return new int[]{};
        }
        int[] res = new int[len];
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                if (nums[j] > nums[i]) {
                    res[i] = nums[j];
                    break;
                }
                res[i] = -1;
            }
        }
        return res;
    }

    int knapsack(int[] wt, int[] val, int size) {
        int[][] dp = new int[wt.length][size];
        for (int i = 0; i < size; i++) {
            dp[0][i] = 0;
        }
        for (int i = 0; i < size; i++) {
            dp[i][0] = 0;
        }

        for (int i = 1; i <= wt.length; i++) {


        }

        return 0;
    }

    int minDisstance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        if (m * n == 0) {
            return m + n;
        }
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            dp[i][0] = dp[i - 1][0] + 1;
        }
        for (int j = 1; j <= n; j++) {
            dp[0][j] = dp[0][j - 1] + 1;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = Math.min(Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1), dp[i - 1][j - 1]) + 1;
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - 1]);
                }
            }
        }
        return dp[m][n];
    }

    int maxProfit(int[] prices) {
        // dp[i] = max(dp[i-1], price[i]-min(price[0:i]))
        if (prices.length < 1) {
            return 0;
        }
        int profit = Integer.MIN_VALUE;
        for (int i = 0; i < prices.length; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                if (prices[j] - prices[i] > 0) {
                    profit = Math.max(profit, prices[j] - prices[i]);
                }
            }
        }
        return profit == Integer.MIN_VALUE ? 0 : profit;
    }

    int maxProfit1(int[] prices) {
        if (prices.length < 1) {
            return 0;
        }
        int[] dp = new int[prices.length];
        dp[0] = 0;
        int cost = prices[0];
        for (int i = 1; i < prices.length; i++) {
            cost = Math.min(cost, prices[i]);
            dp[i] = Math.max(dp[i - 1], prices[i] - cost);
        }
        return dp[prices.length - 1];
    }


    public int removeDuplicates(int[] nums) {
        int n = nums.length;
        if (n < 1) {
            return 0;
        }
        int fast = 1;
        int slow = 1;
        while (fast < n) {
            if (nums[fast] != nums[fast - 1]) {
                nums[slow] = nums[fast];
                ++slow;
            }
            fast++;
        }
        return slow;
    }

    public int longestCommonSubsequence(String text1, String text2) {
        //dp[i][j] = max(dp[i-1][j],dp[i][j-1],dp[i-1][j-1])+ 1
        int m = text1.length();
        int n = text2.length();
        if (m == 0 || n == 0) {
            return 0;
        }
        int[][] dp = new int[m][n];
        for (int i = 1; i < m; i++) {
            dp[i - 1][0] = 0;
        }
        for (int j = 1; j < n; j++) {
            dp[0][j - 1] = 0;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.max(Math.max(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]);
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    public int max(int[] prices) {
        int min = Integer.MAX_VALUE;
        int idx = -1;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < min) {
                min = prices[i];
                idx = i;
            }
        }
        if (idx == -1) {
            return 0;
        }
        int maxVal = 0;
        for (int i = idx; i < prices.length; i++) {
            maxVal = Math.max(maxVal, prices[i]);
        }
        return maxVal - min;
    }


    public int uniquePaths(int m, int n) {
        int[][] memo = new int[m][n];
        //dp[i][j] = dp[i-1][j] + dp[i][j-1]
        return uniquePathsHelp(m - 1, n - 1, memo);
    }

    int uniquePathsHelp(int i, int j, int[][] memo) {
        if (i == 0 || j == 0) {
            memo[i][j] = 1;
        }
        memo[i][j] = uniquePathsHelp(i - 1, j, memo) + uniquePathsHelp(i, j - 1, memo);
        return memo[i][j];
    }

    int maxSubArray(int[] nums) {
        //dp[i] = dp[i-1]
        if (nums.length < 1) {
            return 0;
        }
        int[] dp = new int[nums.length];
        int[] back = new int[nums.length];
        dp[0] = nums[0];
        int max = Integer.MIN_VALUE;
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);

            if (nums[i] > dp[i - 1] + nums[i]) {
                dp[i] = nums[i];
                back[i] = 0;
            } else {
                dp[i] = dp[i - 1] + nums[i];
            }
            max = Math.max(dp[i], max);
        }
        return max;
    }

    int lis(int[] nums) {
        //dp[i]以nums[i]结尾的最长递增子序列 dp[i]  dp[i-1]
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        int res = Integer.MIN_VALUE;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
                res = Math.max(res, dp[i]);
            }
        }
        return res;
    }

    public int strStr(String haystack, String needle) {
        //hello ll
        if (needle == null || needle.length() == 0) {
            return 0;
        }
        if (haystack == null) {
            return -1;
        }
        int n = haystack.length();
        int m = needle.length();
        boolean flag = true;
        for (int i = 0; i + m <= n; i++) {
            for (int j = 0; j < m; j++) {
                if (haystack.charAt(i + j) != needle.charAt(j)) {
                    flag = false;
                    break;

                }
            }
            if (flag) {
                return i;
            }
        }
        return -1;
    }

    public int findLength(int[] nums1, int[] nums2) {
        //dp[i][j] = dp[i-1][j-1] + 1
        int m = nums1.length;
        int n = nums2.length;
        if (m == 0 || n == 0) {
            return 0;
        }
        int[][] dp = new int[m + 1][n + 1];
//        for (int i = 0; i <= m; i++) {
//            dp[i][0] = 0;
//        }
//        for (int j = 0; j < n; j++) {
//            dp[0][j] = 0;
//        }
        int res = dp[0][0];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                } else {
                    if (nums1[i - 1] == nums1[j - 1]) {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    } else {
                        dp[i][j] = 0;
                    }
                    res = Math.max(dp[i][j], res);
                }
            }
        }
        return res;
    }

    int maxMulti(int[] nums) {
        int n = nums.length;
        if (n < 1) {
            return 0;
        }
        int[] maxDp = new int[n];
        int[] minDp = new int[n];
        maxDp[0] = nums[0];
        minDp[0] = nums[0];
        int res = maxDp[0];
        for (int i = 1; i < n; i++) {
            maxDp[i] = Math.max(nums[i], Math.max(maxDp[i - 1] * nums[i], minDp[i - 1] * nums[i]));
            minDp[i] = Math.min(nums[i], Math.min(maxDp[i - 1] * nums[i], minDp[i - 1] * nums[i]));
            res = Math.max(res, maxDp[i]);
        }
        return res;
    }

    public int minimumTotal(List<List<Integer>> triangle) {
        //dp[i][j] = min(dp[i+11][j],  dp[i+11][j+1]) + triangle[i][j]
        int m = triangle.size();
        if (m == 0) {
            return 0;
        }
        int[][] dp = new int[m + 1][m + 1];
        for (int i = m - 1; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                dp[i][j] = Math.min(dp[i + 1][j], dp[i + 1][j + 1]) + triangle.get(i).get(j);
            }
        }
        return dp[0][0];
    }

    int minimumTotalHelp(List<List<Integer>> triangle, int[][] memo, int i, int j) {
        //最下面的数据0
        if (triangle.size() == i) {
            return 0;
        }
        memo[i][j] = Math.min(minimumTotalHelp(triangle, memo, i + 1, j), minimumTotalHelp(triangle, memo, i + 1, j + 1)) + triangle.get(i).get(j);
        return memo[i][j];
    }


    int longestTurbulentSubArray(int nums) {
        /**
         * dp[i] =
         */
        return 0;
    }


    boolean isPalindromic(String s) {
        if (s == null) {
            return true;
        }
        int i = 0;
        int j = s.length() - 1;
        s = s.toLowerCase();
        while (i <= j) {
            if (Character.isLetterOrDigit(s.charAt(i)) && Character.isLetterOrDigit(s.charAt(j))) {
                if (s.charAt(i) != s.charAt(j)) {
                    return false;
                }
                i++;
                j--;
            } else if (!Character.isLetterOrDigit(s.charAt(i))) {
                i++;
            } else if (!Character.isLetterOrDigit(s.charAt(j))) {
                j--;
            } else {
                i++;
                j--;
            }
        }
        return true;
    }

    public int numDecodings1(String s) {
        if (s == null) {
            return 1;
        }
        int n = s.length();
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            if (s.charAt(i - 1) != '0') {
                dp[i] += dp[i - 1];
            }
            if (i > 1 && s.charAt(i - 2) != '0' && (s.charAt(i - 2) - '0') * 10 + s.charAt(i - 1) <= 26) {
                dp[i] += dp[i - 2];
            }
        }
        return dp[n];
    }

    public int countSubstrings(String s) {
        int n = s.length();
        if (s == null || n == 0) {
            return 0;
        }
        boolean[][] dp = new boolean[n][n];
        int cnt = 0;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    if (j - i < 2) {
                        dp[i][j] = true;
                        cnt++;
                    } else {
                        if (dp[i + 1][j - 1]) {
                            dp[i][j] = true;
                            cnt++;
                        }
                    }
                }
            }
        }
        return cnt;
    }


    public List<Integer> largestDivisibleSubset(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return Collections.emptyList();
        }
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                break;
            }
        }
        return Collections.emptyList();
    }

    public int combinationSum4(int[] nums, int target) {
        //dp[i] = sum(dp[target - nums[i]])
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 1; i <= target; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (nums[j] <= i) {
                    dp[i] += dp[target - nums[j]];
                }
            }
        }
        return dp[target];
    }

    public int waysToChange1(int n) {
        if (n < 1) {
            return 0;
        }
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 0; i < coins.length; i++) {
            for (int j = coins[i]; j <= n; j++) {
                dp[j] += dp[j - coins[j]];
            }
        }
        return dp[n];
    }

    public int coinChange(int[] coins, int amount) {
        if (coins.length == 0) {
            return -1;
        }
        int[] dp = new int[amount + 1];
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            int res = Integer.MAX_VALUE;
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i && dp[i - coins[j]] < res) {
                    res = dp[i - coins[j]] + 1;
                }
            }
            dp[i] = res;
        }
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }

    String reverseString(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        char[] chars = s.toCharArray();
        int i = 0;
        int j = chars.length - 1;
        while (i <= j) {
            if (!Character.isLetter(chars[i])) {
                i++;
            }
            if (!Character.isLetter(chars[j])) {
                j--;
            }
            if (Character.isLetter(chars[i]) && Character.isLetter(chars[j])) {
                char temp = chars[i];
                chars[i] = chars[j];
                chars[j] = temp;
                i++;
                j--;
            }
        }
        return new String(chars);
    }

    public int combinationSum41(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 1; i <= target; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (nums[j] <= i) {
                    dp[i] += dp[i - nums[j]];
                }
            }
        }
        return dp[target];
    }

    public int shipWithinDays(int[] weights, int D) {
        int left = Arrays.stream(weights).max().getAsInt();
        int right = Arrays.stream(weights).sum();
        while (left < right) {
            int mid = (left + right) / 2;
            int need = 1;
            int cur = 0;
            for (int weight : weights) {
                if (cur + weight > mid) {
                    need++;
                    cur = 0;
                }
                cur += weight;
            }
            if (need <= D) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }


    int sum = 0;

    public int rangeSumBST(TreeNode root, int low, int high) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        int sum = 0;
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node == null) {
                continue;
            }
            if (node.val < low) {
                queue.offer(root.right);
            } else if (node.val > high) {
                queue.offer(root.left);
            } else {
                queue.offer(root.left);
                queue.offer(root.left);
                sum += node.val;
            }
        }
        return sum;
    }

    void rangeSumBSTHelper(TreeNode root, int low, int high) {
        if (root == null) {
            return;
        }
        rangeSumBST(root.left, low, high);
        if (root.val > low && root.val < high) {
            sum += root.val;
        }
        rangeSumBST(root.right, low, high);
    }

    public boolean judgeSquareSum(int c) {
        int left = 0;
        long right = (long) Math.sqrt(c);
        while (left <= right) {
            long sum = left * left + right * right;
            if (sum < c) {
                left = left + 1;
            } else if (sum > c) {
                right = right - 1;
            } else {
                return true;
            }
        }
        return false;
    }

    public boolean canCross(int[] stones) {
        int n = stones.length;
        if (stones[1] != 1) {
            return false;
        }
        boolean[][] dp = new boolean[n][n];
        dp[1][1] = true;
        for (int i = 2; i < n; i++) {
            for (int j = 1; j < i; j++) {
                int k = stones[i] - stones[j];
                if (k < j + 1) {
                    dp[i][k] = dp[j][k - 1] || dp[j][k + 1] || dp[j][k];
                }
            }
        }
        for (int i = 1; i < n; i++) {
            if (dp[n - 1][i]) {
                return true;
            }
        }
        return false;
    }

    List<String> res = new LinkedList<>();
    char[] c;

    public String[] permutation(String s) {
        if (s == null || s.length() == 0) {
            return new String[]{};
        }
        c = s.toCharArray();
        dfs(0);
        return res.toArray(new String[res.size()]);
    }

    void dfs(int x) {
        if (x == c.length - 1) {
            res.add(String.valueOf(c));
        }
        HashSet<Character> set = new HashSet<>();
        for (int i = x; i < c.length; i++) {
            if (set.contains(c[i])) {
                continue;
            }
            set.add(c[i]);
            swap(i, x);
            dfs(x + 1);
            swap(i, x);
        }
    }

    private void swap(int i, int x) {
        char temp = c[i];
        c[i] = c[x];
        c[x] = temp;
    }

    public int singleNumber(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                map.put(nums[i], map.get(nums[i]) + 1);
            } else {
                map.put(nums[i], 0);
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (map.get(nums[i]) == 1) {
                return nums[i];
            }
        }
        return -1;
    }


    public int getImportance(List<Employee> employees, int id) {
        Optional<Employee> first = employees.stream().filter(e -> e.id == id).findFirst();
        first.ifPresent(e -> {
            sum += e.importance;
            for (Integer eid : e.subordinates) {
                getImportance(employees, eid);
            }
        });
        return sum;
    }
}

class Employee {
    public int id;
    public int importance;
    public List<Integer> subordinates;
}

