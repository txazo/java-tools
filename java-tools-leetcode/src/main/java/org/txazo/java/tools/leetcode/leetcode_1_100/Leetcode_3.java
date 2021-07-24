package org.txazo.java.tools.leetcode.leetcode_1_100;

import java.util.HashMap;
import java.util.Map;

/**
 * 3、无重复字符的最长子串
 * <p>
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
 * <p>
 * https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
 */
public class Leetcode_3 {

    public int lengthOfLongestSubstring(String s) {
        int max = 0;
        int left = 0;
        // 哈希表
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.containsKey(s.charAt(i))) {
                // 重置left
                left = Math.max(left, map.get(c) + 1);
            }
            // 字符c的下标覆盖为最新
            map.put(c, i);
            max = Math.max(max, i - left + 1);
        }
        return max;
    }

}
