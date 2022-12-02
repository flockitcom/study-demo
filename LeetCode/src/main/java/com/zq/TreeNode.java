package com.zq;

/**
 * @author zhangqian
 */
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(30);
        TreeNode node2 = new TreeNode(36);
        TreeNode node3 = new TreeNode(21);
        TreeNode node4 = new TreeNode(36);
        TreeNode node5 = new TreeNode(35);
        TreeNode node6 = new TreeNode(26);
        TreeNode node7 = new TreeNode(15);
        TreeNode node8 = new TreeNode(33);
        TreeNode node9 = new TreeNode(8);
        TreeNode node10 = new TreeNode(1);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;
        node5.right = node8;
        node7.right = node9;
//        node6.right = node10;
    }
}
