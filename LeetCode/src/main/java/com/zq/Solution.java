package com.zq;

class Solution {

    TreeNode pre = null;
    TreeNode deleteNode;

    public TreeNode deleteNode(TreeNode root, int key) {
        tree(root, key);
        return root;
    }

    private void tree(TreeNode root, int val) {

        if (val < root.val) {
        }
    }


    public static void main(String[] args) {

        TreeNode node1 = new TreeNode(5);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(6);
        TreeNode node4 = new TreeNode(2);
        TreeNode node5 = new TreeNode(4);
        TreeNode node6 = new TreeNode(7);
        TreeNode node7 = new TreeNode(9);
        TreeNode node8 = new TreeNode(3);
        TreeNode node9 = new TreeNode(5);
        TreeNode node10 = new TreeNode(1);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.right = node6;
//        node3.right = node7;
//        node5.left = node8;
//        node5.right = node9;
//        node6.right = node10;

        Solution solution = new Solution();
        Object o = solution.deleteNode(node1, 3);
        System.out.println(o);
    }
}

