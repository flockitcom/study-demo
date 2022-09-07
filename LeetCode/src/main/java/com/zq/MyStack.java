package com.zq;

import util.Stack;

import java.util.ArrayDeque;
import java.util.Deque;

class MyStack {
    Deque<Integer> queue;

    public MyStack() {
        queue = new ArrayDeque<Integer>();
    }

    public void push(int x) {
        queue.add(x);
    }

    public int pop() {
        for (int i = 0; i < queue.size() - 1; i++) {
            queue.add(queue.poll());
        }
        return queue.poll();
    }

    public int top() {
        for (int i = 0; i < queue.size() - 1; i++) {
            queue.add(queue.poll());
        }
        Integer poll = queue.poll();
        queue.add(poll);
        return poll;
    }

    public boolean empty() {
        return queue.size() == 0;
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack.pop());
        System.out.println(stack.empty());
        System.out.println(stack.peek());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.empty());
    }
}