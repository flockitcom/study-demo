package util;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 栈
 *
 * @author zhangqian
 */
public class Stack<E> {

    private Deque<E> queue;

    public Stack() {
        queue = new ArrayDeque<>();
    }

    public void push(E e) {
        queue.addLast(e);
    }

    public E pop() {
        return queue.pollLast();
    }

    public E peek() {
        return queue.peekLast();
    }

    public boolean isEmpty() {
        return queue.size() == 0;
    }
}
