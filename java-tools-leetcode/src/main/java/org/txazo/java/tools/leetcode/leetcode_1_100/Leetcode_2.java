package org.txazo.java.tools.leetcode.leetcode_1_100;

/**
 * 2、两数相加
 * <p>
 * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
 * <p>
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 * <p>
 * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * <p>
 * https://leetcode-cn.com/problems/add-two-numbers/
 */
public class Leetcode_2 {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 虚拟头节点
        ListNode head = new ListNode(0);
        // 单指针
        ListNode curr = head;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int n1 = (l1 != null) ? l1.val : 0;
            int n2 = (l2 != null) ? l2.val : 0;
            int sum = n1 + n2 + carry;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;

            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        if (carry == 1) {
            curr.next = new ListNode(1);
        }
        return head.next;
    }

}
