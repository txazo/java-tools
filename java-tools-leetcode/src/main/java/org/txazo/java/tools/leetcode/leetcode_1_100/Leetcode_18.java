package org.txazo.java.tools.leetcode.leetcode_1_100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 18、四数之和
 * <p>
 * 给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，使得 a + b + c + d 的值与 target 相等？找出所有满足条件且不重复的四元组。
 * <p>
 * 注意：答案中不可以包含重复的四元组。
 * <p>
 * https://leetcode-cn.com/problems/4sum/
 */
public class Leetcode_18 {

    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length < 4) {
            return res;
        }
        // 排序
        Arrays.sort(nums);
        int length = nums.length;
        for (int i = 0; i < length - 3; i++) {
            // 去重
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            // 极小值大于target
            if (nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target) {
                break;
            }
            // 极大值小于target
            if (nums[i] + nums[length - 3] + nums[length - 2] + nums[length - 1] < target) {
                continue;
            }
            for (int j = i + 1; j < length - 2; j++) {
                // 去重
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                // 极小值大于target
                if (nums[i] + nums[j] + nums[j + 1] + nums[j + 2] > target) {
                    break;
                }
                // 极大值小于target
                if (nums[i] + nums[j] + nums[length - 2] + nums[length - 1] < target) {
                    continue;
                }
                // 双指针
                int L = j + 1;
                int R = nums.length - 1;
                while (L < R) {
                    int sum = nums[i] + nums[j] + nums[L] + nums[R];
                    if (sum == target) {
                        res.add(Arrays.asList(nums[i], nums[j], nums[L], nums[R]));
                        while (L < R && nums[L] == nums[L + 1]) {
                            L++;
                        }
                        while (L < R && nums[R] == nums[R - 1]) {
                            R--;
                        }
                        L++;
                        R--;
                    } else if (sum > target) {
                        R--;
                    } else {
                        L++;
                    }
                }
            }
        }
        return res;
    }

}
