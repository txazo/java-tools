package org.txazo.java.tools.leetcode.sword_to_offer;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author tuxiaozhou
 * @date 2022/3/3
 */
public class SwordToOffer13 {

    public static int moveCount(int m, int n, int k) {
        int count = 0;
        boolean[][] moved = new boolean[m][n];
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{0, 0});
        while (!queue.isEmpty()) {
            int[] node = queue.poll();
            int x = node[0];
            int y = node[1];
            if (canMove(m, n, x + 1, y, k, moved)) {
                queue.add(new int[]{x + 1, y});
                count++;
            } else if (canMove(m, n, x, y + 1, k, moved)) {
                queue.add(new int[]{x, y + 1});
                count++;
            }
        }
        return count;
    }

    private static boolean canMove(int m, int n, int x, int y, int k, boolean[][] moved) {
        if (x < 0 || x >= m || y < 0 || y >= n || moved[x][y] || (size(x) + size(y)) > k) {
            return false;
        }
        moved[x][y] = true;
        return true;
    }

    private static int size(int i) {
        return -1;
    }

}
