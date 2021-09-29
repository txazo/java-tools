package org.txazo.java.tools.leetcode.common;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 二叉树BFS模板
 * <p>
 * 二叉树层序遍历
 * 网格最短路径: 网格遍历，每个格子向上下左右四个格子扩散
 * 多源BFS
 */
public class BFSTemplate {

    public void bfs(TreeNode root) {
        if (root == null) {
            return;
        }
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
    }

}
