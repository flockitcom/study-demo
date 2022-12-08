package com.zq;

class Solution {

    public int findMaxForm(String[] strs, int m, int n) {
        int[][] dp = new int[m + 1][n + 1];

        int firstM = 0;
        int firstN = 0;
        for (int j = 0; j < strs[0].length(); j++) {
            if (strs[0].charAt(j) == '0') {
                firstM++;
            } else {
                firstN++;
            }
        }
        dp[firstM][firstN] = 1;

        for (int i = 1; i < strs.length; i++) {
            int size0 = 0;
            int size1 = 0;
            for (int j = 0; j < strs[i].length(); j++) {
                if (strs[i].charAt(j) == '0') {
                    size0++;
                } else {
                    size1++;
                }
            }

            for (int j = m; j >= 1; j--) {
                for (int k = n; k >= 1; k--) {
                    int have = 0;
                    if (j >= size0 && k >= size1) {
                        have = dp[j - size0][k - size1] + 1;
                    }
                    if (have == dp[j][k] && have != 0) {
                        dp[j][k] = dp[j][k] + 1;
                    } else {
                        dp[j][k] = Math.max(dp[j][k], have);
                    }

                }
            }
        }
        return dp[m][n];
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
        Object o = solution.findMaxForm(new String[]{"10", "0001", "111001", "1", "0"}, 5, 3);
        System.out.println(o);
    }
}

