package org.txazo.java.tools.leetcode.leetcode_1_100;

/**
 * 5、最长回文子串
 * <p>
 * 给你一个字符串 s，找到 s 中最长的回文子串。
 * <p>
 * https://leetcode-cn.com/problems/longest-palindromic-substring/
 */
public class Leetcode_5 {

    /**
     * 中心扩散法
     */
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) return "";
        // [0]，回文子串的起始位置
        // [1]，回文子串的长度
        int[] max = new int[2];
        for (int i = 0; i < s.length(); i++) {
            int[] len1 = expandAroundCenter(s, i, i);
            int[] len2 = expandAroundCenter(s, i, i + 1);
            int[] len = len1[1] > len2[1] ? len1 : len2;
            if (len[1] > max[1]) {
                max = len;
            }
        }
        return s.substring(max[0], max[0] + max[1]);
    }

    private int[] expandAroundCenter(String s, int left, int right) {
        // 双指针
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return new int[]{left + 1, right - left - 1};
    }

}
