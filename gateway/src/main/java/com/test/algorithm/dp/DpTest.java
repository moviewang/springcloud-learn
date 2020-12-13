package com.test.algorithm.dp;

import java.util.*;

/**
 * @Author: movie
 * @Date: 2020/5/1 17:40
 */
public class DpTest {
    public static void main(String[] args) {

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


    public int maxSubArray(int[] nums) {
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
    public int maxSubArray1(int[] nums) {
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
}
