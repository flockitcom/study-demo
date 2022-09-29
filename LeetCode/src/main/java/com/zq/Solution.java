package com.zq;

class Solution {

    public int largestRectangleArea(int[] heights) {
        int[] leftIndexMin = new int[heights.length];
        int[] rightIndexMin = new int[heights.length];
        leftIndexMin[0] = -1;
        for (int i = 1; i < heights.length; i++) {
            int left = i - 1;
            while (left >= 0 && heights[left] >= heights[i]) {
                left = leftIndexMin[left];
            }
            leftIndexMin[i] = left;
        }

        rightIndexMin[heights.length - 1] = heights.length;
        for (int i = heights.length - 2; i >= 0; i--) {
            int right = i + 1;
            while (right <= heights.length - 1 && heights[right] >= heights[i]) {
                right = rightIndexMin[right];
            }
            rightIndexMin[i] = right;
        }

        int max = 0;
        for (int i = 0; i < heights.length; i++) {
            max = Math.max(max, (rightIndexMin[i] - leftIndexMin[i] - 1) * heights[i]);
        }
        return max;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        Object o = solution.largestRectangleArea(new int[]{2, 1, 5, 6, 2, 3});
        System.out.println("" + o);
    }
}

