package org.txazo.java.tools.leetcode.leetcode_1_100;

import java.util.ArrayList;
import java.util.List;

/**
 * 77、组合
 * <p>
 * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
 * <p>
 * 你可以按 任何顺序 返回答案。
 * <p>
 * https://leetcode-cn.com/problems/combinations/
 */
public class Leetcode_77 {

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        backtrace(res, 1, n, k, new ArrayList<>());
        return res;
    }

    private void backtrace(List<List<Integer>> res, int start, int n, int k, List<Integer> track) {
        // 剪枝
        if (track.size() + (n - start + 1) < k) {
            return;
        }
        if (track.size() == k) {
            res.add(new ArrayList<>(track));
            return;
        }

        for (int i = start; i <= n; i++) {
            track.add(i);
            backtrace(res, i + 1, n, k, track);
            track.remove(track.size() - 1);
        }
    }

}
