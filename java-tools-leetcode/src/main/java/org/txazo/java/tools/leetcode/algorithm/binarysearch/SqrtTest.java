package org.txazo.java.tools.leetcode.algorithm.binarysearch;

/**
 * @author tuxiaozhou
 * @date 2021/12/11
 */
public class SqrtTest {

    public static void main(String[] args) {
        System.out.println(sqrt(4));
        System.out.println(sqrt(5));
        System.out.println(sqrt(6));
        System.out.println(sqrt(7));
        System.out.println(sqrt(8));
        System.out.println(sqrt(9));
    }

    public static double sqrt(int k) {
        if (k < 0) {
            return -1D;
        }
        double low = 0;
        double mid = k / 2D;
        double high = k;
        while ((Math.abs(mid * mid - k)) > 0.000001) {
            if (mid * mid < k) {
                low = mid;
            } else {
                high = mid;
            }
            mid = low + (high - low) / 2;
        }
        return mid;
    }

}
