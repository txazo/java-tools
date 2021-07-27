package org.txazo.java.tools.leetcode.common;

/**
 * 二分查找模板
 */
public class BinarySearchTemplate {

    public int binarySearch(int[] nums, int target) {
        // 注意: 0、nums.length - 1
        int lo = 0, hi = nums.length - 1;
        // 注意: <、<=
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                // 注意
                hi = mid - 1;
            } else {
                // 注意
                lo = mid + 1;
            }
        }
        return -1;
    }

}
