package com.zq;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {

    List<List<String>> result = new ArrayList<>();
    List<String> list = new ArrayList<>();
    char[] chars;
    boolean[][] use;

    public List<List<String>> solveNQueens(int n) {
        use = new boolean[n][n];
        chars = new char[n];
        Arrays.fill(chars, '.');
        backTracking(n, 0);
        return result;
    }

    public void backTracking(int n, int startIndex) {
        if (startIndex >= n) {
            result.add(new ArrayList<>(list));
        }
        for (int i = 0; i < n; i++) {
            boolean flag = false;

            //判断列是否存在
            if (!flag) {
                for (int j = 0; j < startIndex; j++) {
                    if (use[j][i]) {
                        flag = true;
                        break;
                    }
                }
            }

            //判断左上是否存在
            if (!flag) {
                int leftMin = Math.min(startIndex, i);
                for (int l = 0; l < leftMin; l++) {
                    if (use[startIndex - l - 1][i - l - 1]) {
                        flag = true;
                        break;
                    }
                }
            }
            if (!flag) {
                //判断右上是否存在
                int rightMin = Math.min(startIndex, n - 1 - i);
                for (int r = 0; r < rightMin; r++) {
                    if (use[startIndex - r - 1][i + r + 1]) {
                        flag = true;
                        break;
                    }
                }
            }

            if (flag) {
                continue;
            }


            chars[i] = 'Q';
            use[startIndex][i] = true;
            list.add(new String(chars));
            chars[i] = '.';
            backTracking(n, startIndex + 1);
            list.remove(list.size() - 1);
            use[startIndex][i] = false;

        }
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        Object o = solution.solveNQueens(4);
        System.out.println("" + o);
    }
}

