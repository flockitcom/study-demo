package com.zq.stream;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * @author zqian
 * @date 2021/3/25
 */
public class T03_IntStream {
    public static void main(String[] args) {
        List<String> list = Lists.newArrayList("a", "b", "c");

        Consumer<String> consumer = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println("print:" + s);
            }
        };

        List<String> collect = list.stream().peek(consumer).skip(2).collect(Collectors.toList());
        System.out.println(collect);
    }
}
