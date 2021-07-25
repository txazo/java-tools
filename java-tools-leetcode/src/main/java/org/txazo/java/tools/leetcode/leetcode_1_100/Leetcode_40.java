package org.txazo.java.tools.leetcode.leetcode_1_100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 40、组合总和 II
 * <p>
 * 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * <p>
 * candidates 中的每个数字在每个组合中只能使用一次。
 * <p>
 * https://leetcode-cn.com/problems/combination-sum-ii/
 */
public class Leetcode_40 {

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (candidates == null || candidates.length < 1) {
            return res;
        }
        Arrays.sort(candidates);
        // 回溯法
        dfs(res, new ArrayList<>(), 0, target, candidates.length, candidates);
        return res;
    }

    private void dfs(List<List<Integer>> res, List<Integer> cur, int begin, int left, int len, int[] candidates) {
        if (left == 0) {
            res.add(new ArrayList<>(cur));
            return;
        }

        for (int i = begin; i < len; i++) {
            // 剪枝
            if (left < candidates[i]) {
                break;
            }
            if (i > begin && candidates[i] == candidates[i - 1]) {
                continue;
            }
            cur.add(candidates[i]);
            dfs(res, cur, i + 1, left - candidates[i], len, candidates);
            cur.remove(cur.size() - 1);
        }
    }

}
