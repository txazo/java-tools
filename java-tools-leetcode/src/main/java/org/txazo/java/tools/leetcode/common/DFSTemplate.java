package org.txazo.java.tools.leetcode.common;

/**
 * 二叉树DFS模板
 */
public class DFSTemplate {

    public void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        // 前序遍历
        dfs(root.left);
        // 中序遍历
        dfs(root.right);
        // 后序遍历
    }

}
