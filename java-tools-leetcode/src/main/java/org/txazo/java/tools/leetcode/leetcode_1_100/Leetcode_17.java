package org.txazo.java.tools.leetcode.leetcode_1_100;

import java.util.ArrayList;
import java.util.List;

/**
 * 17、电话号码的字母组合
 * <p>
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
 * <p>
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 * <p>
 * https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/
 */
public class Leetcode_17 {

    private static final String[] CHAR_MAP = new String[]{
            "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"
    };

    /**
     * 回溯算法
     */
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits == null || digits.length() < 1) {
            return res;
        }
        backtrack(res, digits, 0, new StringBuilder());
        return res;
    }

    private void backtrack(List<String> res, String digits, int index, StringBuilder builder) {
        if (index == digits.length()) {
            res.add(builder.toString());
            return;
        }

        char[] chars = CHAR_MAP[Integer.parseInt(String.valueOf(digits.charAt(index))) - 2].toCharArray();
        for (char c : chars) {
            builder.append(c);
            backtrack(res, digits, index + 1, builder);
            builder.deleteCharAt(builder.length() - 1);
        }
    }

}
