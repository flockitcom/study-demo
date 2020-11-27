package com.zq;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * String与stream缓缓
 * @author: zhangqian
 * @date: 2020/8/26
 */
public class T01 {
    public static void main(String[] args) {
        String s="zhangqian";
        Stream s1 = Stream.of(s);
        String collect = s1.collect(Collectors.joining()).toString();
        System.out.println(collect);
    }
}
