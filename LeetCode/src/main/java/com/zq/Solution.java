package com.zq;

import com.google.common.collect.Lists;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

class Solution {
    public int maxDepth(Node root) {
        if (root == null) {
            return 0;
        }
        Deque<Node> deque = new ArrayDeque<>();
        deque.addFirst(root);
        int lenth = 0;
        while (deque.size() > 0) {
            int size = deque.size();
            lenth++;
            while (size-- > 0) {
                Node node = deque.getLast();
                for (Node child : node.children) {
                    if (child != null) {
                        deque.addFirst(child);
                    }
                }
            }
        }
        return lenth;
    }


    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(3);
        Node node3 = new Node(2);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        node3.children = new ArrayList<>(Lists.newArrayList(node5, node6));

        Solution solution = new Solution();
        int symmetric = solution.maxDepth(node1);
        System.out.println();
    }

}

