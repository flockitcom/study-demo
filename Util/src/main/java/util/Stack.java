package util;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 栈
 *
 * @author zhangqian
 */
public class Stack<E> {

    private Deque<E> stack;

    public Stack() {
        stack = new ArrayDeque<>();
    }

    public void push(E e) {
        stack.push(e);
    }

    public E pop() {
        return stack.pop();
    }

    public E peek() {
        return stack.peek();
    }

    public boolean empty() {
        return stack.size() == 0;
    }
}
