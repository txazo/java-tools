package org.txazo.java.tools.leetcode.leetcode_1_100;

public class Leetcode_5_1 {

    /**
     * 动态规划
     */
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) return "";
        int len = s.length();
        char[] chars = s.toCharArray();
        int max = 1, start = 0;
        boolean[][] dp = new boolean[len][len];
        for (int r = 0; r < len; r++) {
            for (int l = 0; l < r; l++) {
                if (chars[r] == chars[l] &&
                        (r - l <= 2 || dp[l + 1][r - 1])) {
                    dp[l][r] = true;
                    if (r - l + 1 > max) {
                        max = r - l + 1;
                        start = l;
                    }
                }
            }
        }
        return s.substring(start, start + max);
    }

}
