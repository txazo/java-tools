package org.txazo.java.tools.leetcode.algorithm.binarysearch;

/**
 * @author tuxiaozhou
 * @date 2021/12/11
 */
public class BinarySearch {

    public int binSearch(int[] array, int target) {
        if (array == null || array.length < 1) {
            return -1;
        }
        int low = 0;
        int high = array.length - 1;
        // 1、循环退出条件
        while (low <= high) {
            // 2、mid取值
            int mid = low + (high + low) / 2;
            if (array[mid] == target) {
                return mid;
            } else if (array[mid] < target) {
                // 3、low、high更新
                low = mid + 1;
            } else {
                // 3、low、high更新
                high = mid - 1;
            }
        }
        // 4、返回值
        return -1;
    }

}
