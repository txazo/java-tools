package org.txazo.java.tools.leetcode.leetcode_1_100;

/**
 * 36、有效的数独
 * <p>
 * 请你判断一个 9x9 的数独是否有效。只需要 根据以下规则 ，验证已经填入的数字是否有效即可。
 * <p>
 * 数字 1-9 在每一行只能出现一次。
 * 数字 1-9 在每一列只能出现一次。
 * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。（请参考示例图）
 * 数独部分空格内已填入了数字，空白格用 '.' 表示。
 * <p>
 * 注意：
 * <p>
 * 一个有效的数独（部分已被填充）不一定是可解的。
 * 只需要根据以上规则，验证已经填入的数字是否有效即可。
 * <p>
 * https://leetcode-cn.com/problems/valid-sudoku/
 */
public class Leetcode_36 {

    public boolean isValidSudoku(char[][] board) {
        int[][] row = new int[9][10];
        int[][] col = new int[9][10];
        int[][] box = new int[9][10];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                if (c == '.') {
                    continue;
                }
                int number = c - '0';
                if (row[i][number] == 1) {
                    return false;
                }
                if (col[j][number] == 1) {
                    return false;
                }
                int boxIndex = j / 3 + (i / 3) * 3;
                if (box[boxIndex][number] == 1) {
                    return false;
                }
                row[i][number] = 1;
                col[j][number] = 1;
                box[boxIndex][number] = 1;
            }
        }
        return true;
    }

}
