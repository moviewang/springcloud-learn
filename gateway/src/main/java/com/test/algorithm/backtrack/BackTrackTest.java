package com.test.algorithm.backtrack;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author: movie
 * @Date: 2020/6/5 21:53
 */
public class BackTrackTest {

    public static void main(String[] args) {
        int[] nums = {1, 2, 4};
        System.out.println(permute(nums));
    }

    private static List<List<Integer>> res = new LinkedList<>();

    public static List<List<Integer>> permute(int[] nums) {
        LinkedList<Integer> track = new LinkedList<>();
        permuteHelper(nums, track);
        return res;
    }

    private static void permuteHelper(int[] nums, LinkedList<Integer> track) {
        if (track.size() == nums.length) {
            res.add(new LinkedList<>(track));
            return;
        }
        for (int num : nums) {
            if (track.contains(num)) {
                continue;
            }
            track.add(num);
            permuteHelper(nums, track);
            track.removeLast();
        }
    }
}
