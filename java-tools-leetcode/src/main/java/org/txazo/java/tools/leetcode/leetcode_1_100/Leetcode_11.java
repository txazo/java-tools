package org.txazo.java.tools.leetcode.leetcode_1_100;

/**
 * 11、盛最多水的容器
 * <p>
 * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0) 。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * <p>
 * https://leetcode-cn.com/problems/container-with-most-water/
 */
public class Leetcode_11 {

    public int maxArea(int[] height) {
        // 双指针
        int left = 0, right = height.length - 1, res = 0;
        while (left < right) {
            // 每次移动较小的边
            res = height[left] < height[right] ?
                    Math.max(res, (right - left) * height[left++]) :
                    Math.max(res, (right - left) * height[right--]);
        }
        return res;
    }

}
