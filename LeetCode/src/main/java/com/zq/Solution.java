package com.zq;

import java.util.Stack;

class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<Character>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '{' || c == '[') {
                stack.push(getRightParenthesis(c));
            } else {
                if (stack.isEmpty() || stack.pop() != c) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    char getRightParenthesis(char c) {
        switch (c) {
            case '{':
                return '}';
            case '(':
                return ')';
            default:
                return ']';
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        boolean i = solution.isValid("[");
        System.out.println(i);
    }
}
