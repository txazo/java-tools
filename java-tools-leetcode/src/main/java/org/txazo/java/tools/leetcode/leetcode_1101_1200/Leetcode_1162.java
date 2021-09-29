package org.txazo.java.tools.leetcode.leetcode_1101_1200;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 1162、地图分析
 * <p>
 * 你现在手里有一份大小为 N x N 的 网格 grid，上面的每个 单元格 都用 0 和 1 标记好了。其中 0 代表海洋，1 代表陆地，请你找出一个海洋单元格，这个海洋单元格到离它最近的陆地单元格的距离是最大的。
 * <p>
 * 我们这里说的距离是「曼哈顿距离」（ Manhattan Distance）：(x0, y0) 和 (x1, y1) 这两个单元格之间的距离是 |x0 - x1| + |y0 - y1| 。
 * <p>
 * 如果网格上只有陆地或者海洋，请返回 -1。
 * <p>
 * https://leetcode-cn.com/problems/as-far-from-land-as-possible/
 */
public class Leetcode_1162 {

    /**
     * 多源BFS
     */
    public int maxDistance(int[][] grid) {
        int N = grid.length;

        // 所有陆地加入初始队列
        Queue<int[]> queue = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (grid[i][j] == 1) {
                    queue.add(new int[]{i, j});
                }
            }
        }

        // 只有陆地或只有海洋，返回-1
        if (queue.isEmpty() || queue.size() == N * N) {
            return -1;
        }

        // 移动方向
        int[][] moves = {
                {-1, 0}, {1, 0}, {0, -1}, {0, 1},
        };
        int distance = -1;
        while (!queue.isEmpty()) {
            distance++;
            int n = queue.size();
            // BFS
            for (int i = 0; i < n; i++) {
                int[] node = queue.poll();
                for (int[] move : moves) {
                    int x = node[0] + move[0];
                    int y = node[1] + move[1];
                    if (x >= 0 && y >= 0 && x < N && y < N && grid[x][y] == 0) {
                        grid[x][y] = 2;
                        queue.add(new int[]{x, y});
                    }
                }
            }
        }

        return distance;
    }

}
