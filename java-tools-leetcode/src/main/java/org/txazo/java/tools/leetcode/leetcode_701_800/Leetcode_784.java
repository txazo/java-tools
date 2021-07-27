package org.txazo.java.tools.leetcode.leetcode_701_800;

import java.util.ArrayList;
import java.util.List;

/**
 * 784、字母大小写全排列
 * <p>
 * 给定一个字符串S，通过将字符串S中的每个字母转变大小写，我们可以获得一个新的字符串。返回所有可能得到的字符串集合。
 * <p>
 * https://leetcode-cn.com/problems/letter-case-permutation/
 */
public class Leetcode_784 {

    public List<String> letterCasePermutation(String s) {
        List<String> result = new ArrayList<>();
        backtrace(result, 0, s.toCharArray());
        return result;
    }

    private void backtrace(List<String> result, int index, char[] chars) {
        if (index == chars.length) {
            result.add(new String(chars));
            return;
        }

        if (chars[index] < 'A' || chars[index] > 'z') {
            backtrace(result, index + 1, chars);
            return;
        }

        chars[index] = Character.toLowerCase(chars[index]);
        backtrace(result, index + 1, chars);
        chars[index] = Character.toUpperCase(chars[index]);
        backtrace(result, index + 1, chars);
    }

}
