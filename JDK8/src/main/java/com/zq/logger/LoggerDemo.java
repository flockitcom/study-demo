package com.zq.logger;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 日志
 * @author zqian
 * @date 2021/1/14
 */
public class LoggerDemo {
    public static void main(String[] args) {
        List<Integer> a = new ArrayList<>();

        Set<Integer> set = new HashSet<>();
        set.add(1);
        set.add(2);
    }
}

class Solution {
    public int findRepeatNumber(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] != i) {
                if (nums[i] == nums[nums[i]]) {
                    return nums[i];
                }
                nums[i] = nums[i] ^ nums[nums[i]];
                nums[nums[i]] = nums[i] ^ nums[nums[i]];
                nums[i] = nums[i] ^ nums[nums[i]];
            }
        }
        return -1;
    }
}
