package org.txazo.java.tools.leetcode.leetcode_1_100;

import java.util.ArrayList;
import java.util.List;

/**
 * 22、括号生成
 * <p>
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 * <p>
 * https://leetcode-cn.com/problems/generate-parentheses/
 */
public class Leetcode_22 {

    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        // 回溯法
        backtrace(res, n, 0, 0, new StringBuilder());
        return res;
    }

    private void backtrace(List<String> res, int n, int left, int right, StringBuilder builder) {
        if (builder.length() == 2 * n) {
            res.add(builder.toString());
            return;
        }

        if (left < n) {
            builder.append('(');
            backtrace(res, n, left + 1, right, builder);
            builder.deleteCharAt(builder.length() - 1);
        }
        if (right < left) {
            builder.append(')');
            backtrace(res, n, left, right + 1, builder);
            builder.deleteCharAt(builder.length() - 1);
        }
    }

}
