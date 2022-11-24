package com.zq;

class Solution {
    int abs = Integer.MAX_VALUE;

    public int getMinimumDifference(TreeNode root) {

        return 1;
    }


    public static void main(String[] args) {

        TreeNode node1 = new TreeNode(236);
        TreeNode node2 = new TreeNode(104);
        TreeNode node3 = new TreeNode(701);
        TreeNode node4 = new TreeNode(227);
        TreeNode node5 = new TreeNode(911);
        TreeNode node6 = new TreeNode(4);
        TreeNode node7 = new TreeNode(6);
        TreeNode node8 = new TreeNode(3);
        TreeNode node9 = new TreeNode(5);
        TreeNode node10 = new TreeNode(1);
        node1.left = node2;
        node1.right = node3;
        node2.right = node4;
        node3.right = node5;
//        node3.left = node6;
//        node3.right = node7;
//        node5.right = node8;
//        node6.left = node9;
//        node6.right = node10;

        Solution solution = new Solution();
        Object o = solution.getMinimumDifference(node1);
        System.out.println(o);
    }
}

