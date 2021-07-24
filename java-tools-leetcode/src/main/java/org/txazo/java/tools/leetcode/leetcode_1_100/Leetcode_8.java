package org.txazo.java.tools.leetcode.leetcode_1_100;

/**
 * 8、符串转换整数 (atoi)
 * <p>
 * 请你来实现一个 myAtoi(string s) 函数，使其能将字符串转换成一个 32 位有符号整数（类似 C/C++ 中的 atoi 函数）。
 * <p>
 * 函数 myAtoi(string s) 的算法如下：
 * <p>
 * 读入字符串并丢弃无用的前导空格
 * 检查下一个字符（假设还未到字符末尾）为正还是负号，读取该字符（如果有）。 确定最终结果是负数还是正数。 如果两者都不存在，则假定结果为正。
 * 读入下一个字符，直到到达下一个非数字字符或到达输入的结尾。字符串的其余部分将被忽略。
 * 将前面步骤读入的这些数字转换为整数（即，"123" -> 123， "0032" -> 32）。如果没有读入数字，则整数为 0 。必要时更改符号（从步骤 2 开始）。
 * 如果整数数超过 32 位有符号整数范围 [−231,  231 − 1] ，需要截断这个整数，使其保持在这个范围内。具体来说，小于 −231 的整数应该被固定为 −231 ，大于 231 − 1 的整数应该被固定为 231 − 1 。
 * 返回整数作为最终结果。
 * <p>
 * https://leetcode-cn.com/problems/string-to-integer-atoi/
 */
public class Leetcode_8 {

    public int myAtoi(String s) {
        if (s == null || s.length() < 1) {
            return 0;
        }

        // 去掉前面的空格
        char[] chars = s.toCharArray();
        int index = 0;
        while (index < chars.length && chars[index] == ' ') {
            index++;
        }

        // 全部空格的场景
        if (index == chars.length) {
            return 0;
        }

        // 符号位
        int sign = 1;
        char firstChar = chars[index];
        if (firstChar == '+') {
            index++;
        } else if (firstChar == '-') {
            index++;
            sign = -1;
        }

        int res = 0;
        while (index < chars.length) {
            char c = chars[index];
            // 过滤非数字字符
            if (c > '9' || c < '0') {
                break;
            }

            // 溢出判断
            if (res > Integer.MAX_VALUE / 10 ||
                    res == Integer.MAX_VALUE / 10 && (c - '0') > Integer.MAX_VALUE % 10) {
                return Integer.MAX_VALUE;
            }
            if (res < Integer.MIN_VALUE / 10 ||
                    res == Integer.MIN_VALUE / 10 && (c - '0') > -(Integer.MIN_VALUE % 10)) {
                return Integer.MIN_VALUE;
            }

            res = res * 10 + sign * (c - '0');
            index++;
        }

        return res;
    }

}
