package org.txazo.java.tools.leetcode.leetcode_1_100;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * 46、全排列
 * <p>
 * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
 * <p>
 * https://leetcode-cn.com/problems/permutations/
 */
public class Leetcode_46 {

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length < 1) {
            return res;
        }
        backtrace(res, nums.length, nums, new ArrayList<>(), new boolean[nums.length]);
        return res;
    }

    private void backtrace(List<List<Integer>> res, int len, int[] nums, ArrayList<Integer> track, boolean[] used) {
        if (track.size() == len) {
            res.add(new ArrayList<>(track));
            return;
        }
        for (int i = 0; i < len; i++) {
            // 剪枝
            if (used[i]) {
                continue;
            }
            used[i] = true;
            track.add(nums[i]);
            backtrace(res, len, nums, track, used);
            used[i] = false;
            track.remove(track.size() - 1);
        }
    }

}
