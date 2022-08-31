package com.zq;

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

    }
}