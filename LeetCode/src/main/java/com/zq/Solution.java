package com.zq;

import java.util.*;

class Solution {

    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Deque<Integer> deque = new ArrayDeque<>();
        int[] result = new int[nums1.length];
        Arrays.fill(result, -1);
        Map<Integer, Integer> map = new HashMap<>(nums1.length);
        for (int i = 0; i < nums1.length; i++) {
            map.put(nums1[i], i);
        }
        deque.push(0);
        for (int i = 1; i < nums2.length; i++) {
            if (nums2[i] <= nums2[deque.peek()]) {
                deque.push(i);
            } else {
                while (deque.size() > 0 && nums2[i] > nums2[deque.peek()]) {
                    if (map.containsKey(nums2[deque.peek()])) {
                        Integer peek = deque.peek();
                        result[peek] = i - peek;
                    }
                    deque.pop();
                }
                deque.push(i);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        Object o = solution.nextGreaterElement(new int[]{4, 1, 2}, new int[]{1, 3, 4, 2});
        System.out.println("" + o);
    }
}

