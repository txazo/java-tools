package org.txazo.java.tools.leetcode.leetcode_1_100;

/**
 * 43、字符串相乘
 * <p>
 * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 * <p>
 * https://leetcode-cn.com/problems/multiply-strings/
 */
public class Leetcode_43 {

    public String multiply(String num1, String num2) {
        int[] res = new int[num1.length() + num2.length()];
        for (int i = num1.length() - 1; i >= 0; i--) {
            int n1 = num1.charAt(i) - '0';
            for (int j = num2.length() - 1; j >= 0; j--) {
                int n2 = num2.charAt(j) - '0';
                // n1 * n2的结果位于[i + j, i + j + 1]
                // 累加n1 * n2的结果
                int sum = res[i + j + 1] + n1 * n2;
                // 重置i + j + 1
                res[i + j + 1] = sum % 10;
                // i + j进位
                res[i + j] += sum / 10;
            }
        }

        // 去掉前面的0
        int index = 0;
        while (res[index] == 0) {
            if (++index == res.length) {
                return "0";
            }
        }

        StringBuilder result = new StringBuilder();
        for (int i = index; i < res.length; i++) {
            result.append(res[i]);
        }
        return result.toString();
    }

}
