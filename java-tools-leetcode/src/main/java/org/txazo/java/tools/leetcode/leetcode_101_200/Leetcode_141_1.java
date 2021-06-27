package org.txazo.java.tools.leetcode.leetcode_101_200;

public class Leetcode_141_1 {

    /**
     * 反转链表
     */
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        return reverseList(head) == head;
    }

    private ListNode reverseList(ListNode head) {
        ListNode curr = null;
        ListNode next = head;
        while (next != null) {
            // 临时保存下一个next
            ListNode temp = next.next;
            // 指针反转
            next.next = curr;
            // 向前移动curr、next
            curr = next;
            next = temp;
        }
        return curr;
    }

    private static class ListNode {

        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }

    }

}
