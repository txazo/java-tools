package org.txazo.java.tools.leetcode.leetcode_1_100;

/**
 * 24、两两交换链表中的节点
 * <p>
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 * <p>
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * <p>
 * https://leetcode-cn.com/problems/swap-nodes-in-pairs/
 */
public class Leetcode_24 {

    public ListNode swapPairs(ListNode head) {
        // 虚拟头节点
        ListNode pre = new ListNode();
        pre.next = head;
        // 递归法
        swap(pre, head);
        return pre.next;
    }

    private void swap(ListNode pre, ListNode cur) {
        if (cur == null || cur.next == null) {
            return;
        }
        ListNode next = cur.next.next;

        pre.next = cur.next;
        pre.next.next = cur;
        cur.next = next;

        swap(cur, cur.next);
    }

}
