package org.txazo.java.tools.leetcode.leetcode_1_100;

/**
 * 19、删除链表的倒数第 N 个结点
 * <p>
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 * <p>
 * 进阶：你能尝试使用一趟扫描实现吗？
 * <p>
 * https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/
 */
public class Leetcode_19 {

    /**
     * 双指针法
     * 尾递归法
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 虚拟头节点
        ListNode pre = new ListNode();
        pre.next = head;
        // 尾递归法
        reverse(pre, head, n);
        return pre.next;
    }

    private int reverse(ListNode pre, ListNode curr, int n) {
        int index = curr == null ? 0 :
                reverse(pre.next, curr.next, n) + 1;
        if (index == n) {
            // 删除倒数第n个节点
            pre.next = curr.next;
        }
        return index;
    }

}
