package org.txazo.java.tools.leetcode.leetcode_1_100;

/**
 * @author tuxiaozhou
 * @date 2021/7/24
 */
public class Leetcode_33 {

    public int search(int[] nums, int target) {
        int lo = 0, hi = nums.length - 1, mid = 0;
        // 二分搜索
        while (lo <= hi) {
            mid = lo + (hi - lo) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] >= nums[0]) {
                // 左半边有序，[0]判断边界
                if (target >= nums[0] && target < nums[mid]) {
                    hi = mid - 1;
                } else {
                    lo = mid + 1;
                }
            } else {
                // 右半边有序，[0]判断边界
                if (target > nums[mid] && target <= nums[nums.length - 1]) {
                    lo = mid + 1;
                } else {
                    hi = mid - 1;
                }
            }
        }
        return -1;
    }

}
