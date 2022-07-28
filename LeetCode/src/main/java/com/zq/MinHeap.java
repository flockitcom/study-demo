package com.zq;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 小顶堆
 * @author zqian
 * @date 2021/1/21
 */
public class MinHeap {
    public static void main(String[] args) {
        // 初始化小顶堆
        Queue<Integer> heap = new PriorityQueue<Integer>();

        // 元素入堆
        heap.add(1);
        heap.add(2);
        heap.add(4);
        heap.add(2);
        heap.add(6);
        heap.add(8);

        // 元素出堆（从小到大）
        System.out.println(heap.poll()); // -> 1
        System.out.println(heap.poll()); // -> 2
        System.out.println(heap.poll()); // -> 4
        System.out.println(heap.poll()); // -> 6
        System.out.println(heap.poll()); // -> 8
    }
}
