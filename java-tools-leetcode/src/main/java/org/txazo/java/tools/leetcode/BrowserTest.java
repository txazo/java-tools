package org.txazo.java.tools.leetcode;

import java.util.Stack;

/**
 * @author tuxiaozhou
 * @date 2021/12/9
 */
public class BrowserTest {

    public static void main(String[] args) {
        BrowserTest browserTest = new BrowserTest();
        browserTest.click("a");
        browserTest.click("b");
        browserTest.click("c");
        browserTest.back();
        browserTest.back();
        browserTest.back();
        browserTest.forward();
        browserTest.forward();
        browserTest.forward();
        browserTest.back();
        browserTest.click("d");
        browserTest.back();
        browserTest.back();
        browserTest.back();
    }

    private final Stack<String> current = new Stack<>();
    private final Stack<String> next = new Stack<>();

    public void click(String page) {
        current.push(page);
        while (!next.isEmpty()) {
            next.pop();
        }
        System.out.println("点击页面" + current.peek());
    }

    public void back() {
        if (current.size() == 1) {
            System.out.println("无法回退");
        } else {
            next.push(current.pop());
            System.out.println("回退到页面" + current.peek());
        }
    }

    public void forward() {
        if (next.isEmpty()) {
            System.out.println("无法前进");
        } else {
            current.push(next.pop());
            System.out.println("前进到页面" + current.peek());
        }
    }

}
