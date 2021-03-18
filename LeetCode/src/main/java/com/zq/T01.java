package com.zq;

import java.util.Stack;

public class T01 {

    public static void main(String[] args) {
    }

    public int[] reversePrint(ListNode head) {
        Stack<Integer> stack = new Stack<>();
        if (head == null) {
            return new int[0];
        }
        ListNode node = head;
        int size = 0;
        while (node.next != null) {
            stack.push(node.val);
            node = node.next;
            size++;
        }
        stack.push(node.val);
        node = node.next;
        size++;
        int[] num = new int[size];
        for (int i = 0; i < size; i++) {
            num[i] = stack.pop();
        }
        return num;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}