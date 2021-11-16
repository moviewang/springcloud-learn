package com.test.algorithm;

import java.util.*;

/**
 * @Author: movie
 * @Date: 2020/4/1 15:43
 */
public class ArrayTest {
    public static void main(String[] args) {
//        int[] search = {5, 4, 6};
//        int[] dist = {9, 3, 5, 4, 6, 4, 3, 6, 5};
//        int[] dist = {1, 5, 2, 3, 4, 5, 6};
        int[] dist = {0, 1, 5, 5, 4, 3, 4, 5, 6};
//        int[] search = {1, 5, 5, 4, 3, 4, 5, 6};
        int[] search = {1, 1, 1};
//        int[] dist = {1, 1};
        System.out.println(indexOf(search, dist));
        System.out.println();
//        quickSort(dist, 0, dist.length - 1);
        int[] ints = countSort(dist, 7);

        for (int i : ints) {
            System.out.print(i + "\t");
        }
        System.out.println(8 ^ 8);
        System.out.println(8 ^ 0);
        System.out.println(isUglyNumber(6));
        System.out.println(isUglyNumber(7));
        System.out.println(GetUglyNumber_Solution(100));
        //language=JSON
        String jsonStr = "{\n" +
                "  \"hello\": \"world\",\n" +
                "  \"test\": \"daa\\a\"\n" +
                "}";

//        JSONObject jsonObject = JSON.parseObject(jsonStr, Feature.);
//        System.out.println(jsonObject);
    }


    public static int indexOf(int[] search, int[] dist) {
        if (search == null || search.length < 1 || dist == null || dist.length < 1) {
            return -1;
        }

        ArrayList<Integer> integers = new ArrayList<>();
        int jstart = 0;
        for (int i = 0; i < search.length; i++) {
            for (int j = jstart; j < dist.length; j++) {
                if (search[i] == dist[j]) {
                    integers.add(j);
                    jstart = j + 1;
                    break;
                }
            }
        }
        if (integers.size() == search.length && isNaturalOrder(integers)) {
            return integers.get(0);
        }
        return -1;
    }

//    public static int indexOf(int[] search, int[] dist) {
//        if (search == null || search.length < 1 || dist == null || dist.length < 1) {
//            return -1;
//        }
//
//        ArrayList<Integer> integers = new ArrayList<>();
//        for (int i = 0; i < search.length; i++) {
//            for (int j = jstart; j < dist.length; j++) {
//                if (search[i] == dist[j]) {
//                    integers.add(j);
//                    jstart = j + 1;
//                    break;
//                }
//            }
//        }
//        if (integers.size() == search.length) {
//            return integers.get(0);
//        }
//        return -1;
//    }

    public static boolean isNaturalOrder(List<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            if (i < list.size() - 1) {
                if (list.get(i + 1) != list.get(i) + 1) {
                    return false;
                }
            }
        }
        return true;
    }

    Queue<Integer> min = new PriorityQueue<>();

    public int[] smallestK(int[] arr, int k) {
        if (arr == null || arr.length < 1 || k < 1) {
            return new int[]{0};
        }
        for (int i = 0; i < arr.length; i++) {
            min.offer(arr[i]);
        }
        int[] res = new int[k];
        int index = 0;
        while (index < k) {
            res[index] = min.poll();
        }
        return res;
    }

    public int[] smallestK1(int[] arr, int k) {
        if (arr == null || arr.length < 1 || k < 1) {
            return new int[]{};
        }
        if (arr.length < k) {
            return arr;
        }
        int low = 0;
        int high = arr.length - 1;
        while (low < high) {
            int pos = partition(arr, 0, arr.length - 1);
            if (pos == k - 1) {
                break;
            } else if (pos < k - 1) {
                low = pos + 1;
            } else {
                high = pos - 1;
            }
        }
        int[] dest = new int[k];
        System.arraycopy(arr, 0, dest, 0, k);
        return dest;
    }

    public static int partition(int[] arr, int low, int high) {
        int pivot = arr[low];
        while (low < high) {
            while (low < high && pivot <= arr[high]) {
                high--;
            }
            arr[low] = arr[high];
            while (low < high && pivot > arr[low]) {
                low++;
            }
            arr[high] = arr[low];
        }
        arr[low] = pivot;
        return low;
    }

    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int mid = partition(arr, low, high);
            quickSort(arr, low, mid - 1);
            quickSort(arr, mid + 1, high);
        }
    }

    public static int[] countSort(int[] arr, int k) {
        int[] c = new int[k];
        for (int i = 0; i < arr.length; i++) {
            c[arr[i]]++;
        }
        int[] result = new int[arr.length];
        int index = 0;
        for (int i = 0; i < c.length; i++) {
            for (int j = 0; j < c[i]; j++) {
                result[index++] = i;
            }
        }
        return result;
    }

    public void FindNumsAppearOnce(int[] array, int num1[], int num2[]) {
        if (array == null || array.length < 1) {
            return;
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < array.length; i++) {
            if (map.containsKey(array[i])) {
                map.put(array[i], map.get(array[i]) + 1);
            } else {
                map.put(array[i], 1);
            }
        }
        List<Integer> nums = new ArrayList<>();
        map.forEach((k, v) -> {
            if (v == 1) {
                nums.add(k);
            }
        });
        if (nums.size() != 2) {
            return;
        }
        num1[0] = nums.get(0);
        num2[0] = nums.get(1);
    }

    public void FindNumsAppearOnce1(int[] array, int num1[], int num2[]) {
        if (array == null || array.length < 1) {
            return;
        }
        int xor1 = 0;
        for (int i = 0; i < array.length; i++) {
            xor1 ^= array[i];
        }
        int index = 1;
        while ((index & xor1) == 0) {
            index = index << 1;
        }
        int res1 = 0;
        int res2 = 0;
        for (int i = 0; i < array.length; i++) {
            if ((index & array[i]) == 0) {
                res1 ^= array[i];
            } else {
                res2 ^= array[i];
            }
        }
        num1[0] = res1;
        num1[2] = res2;
    }

    public int MoreThanHalfNum_Solution(int[] array) {
        if (array == null || array.length < 1) {
            return 0;
        }
        Arrays.sort(array);
        int num = array[array.length / 2];
        int numOfCount = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == num) {
                numOfCount++;
            }
        }
        if (numOfCount <= array.length / 2) {
            return 0;
        } else {
            return num;
        }
    }

    public int MoreThanHalfNum_Solution1(int[] array) {
        int cnt = 0;
        int num = 0;
        for (int i = 0; i < array.length; i++) {
            if (cnt == 0) {
                num = array[i];
                cnt = 1;
            } else {
                if (num == array[i]) {
                    cnt++;
                } else {
                    cnt--;
                }
            }
        }
        int numOfCount = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == num) {
                numOfCount++;
            }
        }
        if (numOfCount <= array.length / 2) {
            return 0;
        } else {
            return num;
        }
    }

    public int FindGreatestSumOfSubArray(int[] array) {
        if (array == null || array.length < 1) {
            return 0;
        }
        int curSum = 0;
        int maxSum = Integer.MIN_VALUE;
        for (int i = 0; i < array.length; i++) {
            if (curSum < 0) {
                curSum = array[i];
            } else {
                curSum += array[i];
            }
            if (curSum > maxSum) {
                maxSum = curSum;
            }
        }
        return maxSum;
    }

    public int FindGreatestSumOfSubArray1(int[] array) {
        if (array == null || array.length < 1) {
            return 0;
        }
        int[] dp = new int[array.length];
        dp[0] = array[0];
        int max = Integer.MIN_VALUE;
        for (int i = 1; i < array.length; i++) {
            dp[i] = Math.max(dp[i - 1] + array[i], array[i]);
            if (dp[i] > max) {
                max = dp[i];
            }
        }
        return max;
    }

    public int FirstNotRepeatingChar(String str) {
        if (str == null || str.length() < 1) {
            return 0;
        }
        int[] count = new int[256];
        for (int i = 0; i < str.length(); i++) {
            count[str.charAt(i)]++;
        }
        for (int i = 0; i < str.length(); i++) {
            if (count[str.charAt(i)] == 1) {
                return i;
            }
        }
        return 0;
    }

    public static int GetUglyNumber_Solution(int index) {
        int count = 0;
        int num = 0;
        while (count < index) {
            num++;
            if (isUglyNumber(num)) {
                count++;
            }
        }
        return num;
    }

    public static boolean isUglyNumber(int num) {
        if (num < 1) {
            return false;
        }
        while (num % 2 == 0) {
            num = num / 2;
        }

        while (num % 3 == 0) {
            num = num / 3;
        }
        while (num % 2 == 0) {
            num = num / 5;
        }
        return num == 1;
    }

    public static int GetUglyNumber_Solution1(int index) {
        if (index < 1) {
            return 0;
        }
        int p2 = 0, p3 = 0, p5 = 0;
        int[] res = new int[index];
        res[0] = 1;
        for (int i = 1; i < index; i++) {
            res[i] = Math.max(Math.max(res[p2] * 2, res[p3] * 3), res[p5] * 5);
            if (res[i] == res[p2] * 2) {
                p2++;
            }
            if (res[i] == res[p3] * 2) {
                p3++;
            }
            if (res[i] == res[p5] * 2) {
                p5++;
            }
        }
        return res[index - 1];
    }

    public int InversePairs(int[] array) {
        if (array == null || array.length < 1) {
            return 0;
        }
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] > array[j]) {
                    count++;
                }
            }
        }
        return count % 1000000007;
    }

    public int cutRope(int target) {
        return cutRopeHelp(target, 0);
    }

    public int cutRope1(int target) {
        //dp[i] = dp[i-j] * dp[j]
        if (target == 2) {
            return 1;
        }
        if (target == 3) {
            return 2;
        }
        int[] dp = new int[target + 1];
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 3;
        for (int i = 4; i <= target; i++) {
            int max = 0;
            for (int j = 1; j <= i / 2; j++) {
                max = Math.max(max, dp[j] * dp[i - j]);
            }
            dp[i] = max;
        }
        return dp[target];
    }

    public int fib(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 1;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int coin : coins) {
            for (int i = coin; i < amount + 1; i++) {
                dp[i] += dp[i - coin];
            }
        }
        return dp[amount];
    }

    public int maxProfit(int[] prices) {
        int max = 0;
        for (int i = 0; i < prices.length; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                if (prices[j] - prices[i] > max) {
                    max = prices[j] - prices[i];
                }
            }
        }
        return max;
    }

    public int maxProfit1(int[] prices) {
        int max = 0;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < min) {
                min = prices[i];
            }
            if (prices[i] - min > max) {
                max = prices[i] - min;
            }
        }
        return max;
    }

    public int climbStairs(int n) {
        if (n < 2) {
            return 0;
        }
        //dp[i] = dp[i-1] + dp[i-2]
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }


    public int cutRopeHelp(int target, int max) {
        int maxVal = max;
        for (int i = 1; i < target; i++) {
            maxVal = Math.max(maxVal, cutRopeHelp(target - i, target - i));
        }
        return maxVal;
    }


    public ArrayList<ArrayList<Integer>> FindContinuousSequence(int sum) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        for (int i = 0; i < sum; i++) {
            int j = i;
            int tempSum = 0;
            while (tempSum < sum) {
                tempSum += j;
                j++;
            }
            if (tempSum == sum) {
                ArrayList<Integer> list = new ArrayList<>();
                for (int k = 0; k < j; k++) {
                    list.add(k);
                }
                res.add(list);
            }

        }
        return res;
    }

    public ArrayList<ArrayList<Integer>> FindContinuousSequence1(int sum) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        if (sum < 1) {
            return res;
        }
        int left = 1;
        int right = 2;
        int sumVal = left + right;
        while (left < right) {
            if (sumVal < sum) {
                right++;
                sumVal += right;
            } else if (sumVal > sum) {
                sumVal -= left;
                left++;
            } else {
                ArrayList<Integer> list = new ArrayList<>();
                for (int i = left; i < right; i++) {
                    list.add(i);
                }
                res.add(list);
                sumVal -= left;
                left++;
            }
        }
        return res;
    }

    public int middle(int[] arr1, int[] arr2) {
        return 0;
    }

    public int middleHelper(int i, int j) {
        return 1;
    }

    public int[] twoSum(int[] nums, int target) {
        int[] res = {-1, -1};
        if (nums == null || nums.length < 1) {
            return res;
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                res[0] = map.get(nums[i]);
                res[1] = i;
            }
            int tmp = target - nums[i];
            map.put(tmp, i);
        }
        return res;
    }

    public void reverseString(char[] s) {
        if (s == null || s.length < 1) {
            return;
        }
        int i = 0;
        int j = s.length - 1;
        while (i < j) {
            char temp = s[i];
            s[i] = s[j];
            s[j] = temp;
            i++;
            j--;
        }
    }

    public boolean checkValidString(String s) {
        Stack<Integer> leftStack = new Stack<>();
        Stack<Integer> asteriskStack = new Stack<>();

        if (s == null || s.length() == 0) {
            return true;
        }
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                leftStack.push(i);
            } else if (c == '*') {
                asteriskStack.push(i);
            } else {
                if (!leftStack.isEmpty()) {
                    leftStack.pop();
                } else if (!asteriskStack.isEmpty()) {
                    asteriskStack.pop();
                } else {
                    return false;
                }
            }
        }
        while (!leftStack.isEmpty() && !asteriskStack.isEmpty()) {
            Integer leftIndex = leftStack.pop();
            Integer asterisKIndex = asteriskStack.pop();
            if (leftIndex > asterisKIndex) {
                return false;
            }
        }
        return leftStack.isEmpty();
    }

    public boolean isValid(String s) {
        if (s == null) {
            return false;
        }
        while (s.contains("{}") || s.contains("[]") || s.contains("()")) {
            s = s.replaceAll("\\{\\}", "");
            s = s.replaceAll("\\[\\]", "");
            s = s.replaceAll("\\(\\)", "");
        }
        return s.isEmpty();
    }

}