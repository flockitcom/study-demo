package com.zq;


import java.math.BigDecimal;

public class T01 {

    public static void main(String[] args) {
    }

    public int removeElement(int[] nums, int val) {
        int index = 0;
        int num = 0;
        int length=nums.length;
        while (index + num < length) {
            if (nums[index] == val) {
                if (index >= length - 1) {
                    return index;
                }
                int temp = index + 1;
                while (nums[temp] == val) {
                    if (temp + 1 < length) {
                        temp++;
                    } else {
                        return index;
                    }
                }
                nums[index] = nums[index] ^ nums[temp];
                nums[temp] = nums[index] ^ nums[temp];
                nums[index] = nums[index] ^ nums[temp];
            }
            index++;
        }
        return index;
    }
}