package com.zq;

import java.util.HashMap;
import java.util.Map;

class Solution {

    Map<Integer, Integer> map;  // 方便根据数值查找位置

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }

        return tree(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
    }

    public TreeNode tree(int[] inorder, int inStart, int inStop, int[] postorder, int poStart, int poStop) {
        if (inStart == inStop) {
            TreeNode treeNode = new TreeNode();
            treeNode.val = inorder[inStart];
            return treeNode;
        }

        int rootIndex = map.get(postorder[poStop]);

        int leftStart = inStart;
        int leftStop = rootIndex == inStart ? inStart - 1 : rootIndex - 1;
        int rightStart = rootIndex == inStop ? inStop + 1 : rootIndex + 1;
        int rightStop = inStop;

        TreeNode middleNode = new TreeNode();
        middleNode.val = inorder[rootIndex];

        if (leftStop >= leftStart) {
            middleNode.left = tree(inorder, leftStart, leftStop, postorder, poStart, rootIndex - 1);
        }
        if (rightStop >= rightStart) {
            middleNode.right = tree(inorder, rightStart, rightStop, postorder, rootIndex, poStop - 1);
        }

        return middleNode;
    }


    public static void main(String[] args) {

        TreeNode node1 = new TreeNode(3);
        TreeNode node2 = new TreeNode(9);
        TreeNode node3 = new TreeNode(20);
        TreeNode node4 = new TreeNode(15);
        TreeNode node5 = new TreeNode(7);
        TreeNode node6 = new TreeNode(4);
        TreeNode node7 = new TreeNode(7);
        TreeNode node8 = new TreeNode(2);
        TreeNode node9 = new TreeNode(5);
        TreeNode node10 = new TreeNode(1);
        node1.left = node2;
        node1.right = node3;
        node3.left = node4;
        node3.right = node5;
//        node3.right = node6;
//        node4.left = node7;
//        node4.right = node8;
//        node6.left = node9;
//        node6.right = node10;

        Solution solution = new Solution();
        Object o = solution.buildTree(new int[]{1, 2, 3, 4}, new int[]{3, 2, 4, 1});
        System.out.println(o);
    }
}

