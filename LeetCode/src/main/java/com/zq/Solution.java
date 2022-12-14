package com.zq;

class Solution {

    public int change(int amount, int[] coins) {
        if (amount == 0) {
            return 1;
        }

        if (coins.length == 1) {
            return amount % coins[0] == 0 ? 1 : 0;
        }

        int[] dp = new int[amount + 1];

        //初始化
        for (int i = 0; i <= amount; i++) {
            if (i % coins[0] == 0) {
                dp[i] = 1;
            }
        }

        for (int i = 1; i < coins.length; i++) {
            for (int j = coins[i]; j <= amount; j++) {
                dp[j] = dp[j] + dp[j - coins[i]];
            }
        }
        return dp[amount];
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

        Solution solution = new Solution();
        Object o = solution.change(500, new int[]{2, 7});
        System.out.println(o);
    }
}

