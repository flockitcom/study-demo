package com.zq;

class Solution {
    public boolean repeatedSubstringPattern(String s) {
        int len = s.length();
        if (len == 0) {
            return false;
        }
        int[] next = new int[len];
        int i = 0;
        next[0] = 0;
        for (int j = 1; j < len; j++) {
            while (i > 0 && s.charAt(i) != s.charAt(j)) {
                i = next[i - 1];
            }
            if (s.charAt(i) == s.charAt(j)) {
                i++;
            }
            next[j] = i;
        }
        if (next[len - 1] > 0 && len % (len - next[len - 1]) == 0) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        boolean i = solution.repeatedSubstringPattern("aabaabaab");
        System.out.println(i);
    }
}
