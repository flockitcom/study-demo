package com.zq;

class Solution {

    int[][] dp;
    int result = 1;

    public int integerBreak(int n) {
        dp = new int[n][2];

        for (int i = 0; i < n; i++) {
            if (i == 0) {
                dp[i] = new int[]{0, 1};
            } else {
                if (i % 2 != 0) {
                    dp[i][0] = dp[i - 1][0] + 1;
                    dp[i][1] = dp[i - 1][1];
                } else {
                    dp[i][0] = dp[i - 1][0];
                    dp[i][1] = dp[i - 1][1] + 1;
                }
            }
        }
        if (n > 3) {
            maxTake(n);
        } else {
            return dp[n - 1][0] * dp[n - 1][1];
        }
        return result;
    }

    private void maxTake(int nums) {
        if (nums <= 3) {
            result = result * nums;
            return;
        }
        maxTake(dp[nums - 1][0]);
        maxTake(dp[nums - 1][1]);
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
        Object o = solution.integerBreak(8);
        System.out.println(o);
    }
}

