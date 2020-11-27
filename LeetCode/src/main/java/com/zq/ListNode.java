package com.zq;

public class ListNode {

    int val;
    ListNode next;

    public ListNode() {
    }

    public ListNode(int x) {
        val = x;
    }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                ", next=" + next +
                '}';
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(9);
//        ListNode node2 = new ListNode(9);
//        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(1);
        ListNode node5 = new ListNode(9);
        ListNode node6 = new ListNode(9);
        ListNode node7 = new ListNode(9);
        ListNode node8 = new ListNode(9);
        ListNode node9 = new ListNode(9);
        ListNode node10 = new ListNode(9);
//        node1.next = node2;
//        node2.next = node3;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;
        node7.next = node8;
        node8.next = node9;
        node9.next = node10;

        ListNode node = new ListNode();
        node = node.addTwoNumbers(node1, node4);
        System.out.println(node);
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int sum = 0;
        int planc = 0;//位数
        int a = 0;//进位
        int b = 0;
        if (l1.val == 0) {
            return l2;
        }
        if (l2.val == 0) {
            return l1;
        }
        while (l1 != null && l2 != null) {
            int temp = (l1.val + l2.val) + a;
            b = temp % 10;
            a = temp / 10;
            sum = b * (int) Math.pow(10, planc) + sum;
            planc++;
            l1 = l1.next;
            l2 = l2.next;
        }
        if (l1 == null && l2 == null) {
            sum = a * (int) Math.pow(10, planc) + sum;
        }
        while (l1 != null) {
            int temp = l1.val + a;
            b = temp % 10;
            a = temp / 10;
            sum = b * (int) Math.pow(10, planc) + sum;
            planc++;
            if (l1.next != null) {
                l1 = l1.next;
            }else {
                sum = a * (int) Math.pow(10, planc) + sum;
                break;
            }
        }
        while (l2 != null) {
            int temp = l2.val + a;
            b = temp % 10;
            a = temp / 10;
            sum = b * (int) Math.pow(10, planc) + sum;
            planc++;
            if (l2.next != null) {
                l2 = l2.next;
            }else {
                sum = a * (int) Math.pow(10, planc) + sum;
                break;
            }
        }

        ListNode head = new ListNode(-1);
        ListNode pre = head;
        while (sum > 0) {
            int i = sum % 10;
            ListNode node = new ListNode(i);
            pre.next = node;
            pre = pre.next;
            sum = sum / 10;
        }
        return head.next;
    }

}
