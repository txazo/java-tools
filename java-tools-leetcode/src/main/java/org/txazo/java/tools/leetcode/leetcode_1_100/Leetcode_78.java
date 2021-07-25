package org.txazo.java.tools.leetcode.leetcode_1_100;

import java.util.ArrayList;
import java.util.List;

/**
 * 78、子集
 * <p>
 * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
 * <p>
 * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 * <p>
 * https://leetcode-cn.com/problems/subsets/
 */
public class Leetcode_78 {

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length < 1) {
            return res;
        }
        backtrace(res, 0, nums, new ArrayList<>());
        return res;
    }

    private void backtrace(List<List<Integer>> res, int start, int[] nums, List<Integer> track) {
        res.add(new ArrayList<>(track));
        for (int i = start; i < nums.length; i++) {
            track.add(nums[i]);
            backtrace(res, i + 1, nums, track);
            track.remove(track.size() - 1);
        }
    }

}
