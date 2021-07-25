package org.txazo.java.tools.leetcode.leetcode_1_100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 39、 组合总和
 * <p>
 * 给定一个无重复元素的正整数数组 candidates 和一个正整数 target ，找出 candidates 中所有可以使数字和为目标数 target 的唯一组合。
 * <p>
 * candidates 中的数字可以无限制重复被选取。如果至少一个所选数字数量不同，则两种组合是唯一的。 
 * <p>
 * 对于给定的输入，保证和为 target 的唯一组合数少于 150 个。
 * <p>
 * https://leetcode-cn.com/problems/combination-sum/
 */
public class Leetcode_39 {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
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
        if (begin == len) {
            return;
        }
        if (left == 0) {
            res.add(new ArrayList<>(cur));
            return;
        }

        for (int i = begin; i < len; i++) {
            // 剪枝
            if (left < candidates[i]) {
                break;
            }
            cur.add(candidates[i]);
            dfs(res, cur, i, left - candidates[i], len, candidates);
            cur.remove(cur.size() - 1);
        }
    }

}
