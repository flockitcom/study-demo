package com.zq;

class Solution {

    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }

        if (nums.length == 1 && sum == Math.abs(target)) {
            return 1;
        }

        //不可能等于目标和
        if (Math.abs(target) > sum || (sum + target) % 2 != 0) {
            return 0;
        }

        int[] dp = new int[(sum + target) / 2 + 1];

        //初始化

        dp[0] = 1;
        if (nums[0] == 0) {
            dp[nums[0]] = 2;
        } else {
            dp[nums[0]] = 1;
        }

        for (int i = 1; i < nums.length; i++) {
            for (int j = (sum + target) / 2; j >= nums[i]; j--) {
                dp[j] += dp[j - nums[i]];
            }
        }

        return dp[(sum + target) / 2];
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
        Object o = solution.findTargetSumWays(new int[]{0, 0, 0, 0, 0, 0, 0, 0, 1}, 1);
        System.out.println(o);
    }
}

