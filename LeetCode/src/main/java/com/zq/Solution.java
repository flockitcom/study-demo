package com.zq;

import java.util.Arrays;

class Solution {

    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];
        Arrays.sort(nums);

        if (target < nums[0]) {
            return 0;
        }

        for (int i = nums[0]; i <= target; i++) {
            int sum = 0;
            int num = 0;
            for (int j = nums.length - 1; j >= 0; j--) {
                if (i < nums[j]) {
                    continue;
                }
                sum += dp[i - nums[j]];
                if (i - nums[j] != 0) {
                    num++;
                }
            }
            if (num < nums.length) {
                sum++;
            }

            dp[i] = sum;
        }
        return dp[target];
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        Object o = solution.combinationSum4(new int[]{5, 1, 8}, 24);
        System.out.println(o);
    }
}

