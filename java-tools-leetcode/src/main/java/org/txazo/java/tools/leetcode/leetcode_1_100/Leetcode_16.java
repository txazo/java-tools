package org.txazo.java.tools.leetcode.leetcode_1_100;

import java.util.Arrays;

/**
 * 16、最接近的三数之和
 * <p>
 * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。
 * <p>
 * https://leetcode-cn.com/problems/3sum-closest/
 */
public class Leetcode_16 {

    public int threeSumClosest(int[] nums, int target) {
        // 排序
        Arrays.sort(nums);
        int res = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < nums.length - 2; i++) {
            // 去重
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            // 双指针
            int L = i + 1;
            int R = nums.length - 1;
            while (L < R) {
                int sum = nums[i] + nums[L] + nums[R];
                if (Math.abs(sum - target) < Math.abs(res - target)) {
                    res = sum;
                }
                if (sum > target) {
                    // 去重
                    while (L < R && nums[R] == nums[R - 1]) {
                        R--;
                    }
                    R--;
                } else if (sum < target) {
                    // 去重
                    while (L < R && nums[L] == nums[L + 1]) {
                        L++;
                    }
                    L++;
                } else {
                    return sum;
                }
            }
        }
        return res;
    }

}
