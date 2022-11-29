package com.zq;

class Solution {
    public TreeNode sortedArrayToBST(int[] nums) {
        return tree(nums, 0, nums.length);
    }

    private TreeNode tree(int[] nums, int start, int stop) {
        if (start >= stop) {
            return null;
        }
        int middle = (start + stop - 1) / 2;

        TreeNode node = new TreeNode();
        node.val = nums[middle];

        node.left = tree(nums, start, middle);
        node.right = tree(nums, middle + 1, stop);

        return node;
    }


    public static void main(String[] args) {

        TreeNode node1 = new TreeNode(3);
        TreeNode node2 = new TreeNode(0);
        TreeNode node3 = new TreeNode(4);
        TreeNode node4 = new TreeNode(2);
        TreeNode node5 = new TreeNode(1);
        TreeNode node6 = new TreeNode(8);
        TreeNode node7 = new TreeNode(15);
        TreeNode node8 = new TreeNode(7);
        TreeNode node9 = new TreeNode(5);
        TreeNode node10 = new TreeNode(1);
        node1.left = node2;
        node1.right = node3;
        node2.right = node4;
        node4.left = node5;
//        node5.left = node6;
//        node5.right = node7;
//        node6.left = node8;
//        node5.right = node9;
//        node6.right = node10;

        Solution solution = new Solution();
        Object o = solution.sortedArrayToBST(new int[]{-10, -3, 0, 5, 9});
        System.out.println(o);
    }
}

