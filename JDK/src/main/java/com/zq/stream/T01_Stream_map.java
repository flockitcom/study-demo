package com.zq.stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * stream map分散操作 distinct去重
 *
 * @author: zhangqian
 * @date: 2020/8/26
 */
public class T01_Stream_map {
    public static void main(String[] args) {
        String[] sStr = {"Java", "Scala", "C++", "Haskell", "Lisp", "Lispaaa", "JAVA"};
        List<String> collect = Arrays.stream(sStr).collect(Collectors.toList());
        collect.add("23rq541rq3");
        collect.add("safas");
        collect.add("sdgsd");
    }

    @Test
    public void test1() {
        Map<Integer, String> map = IntStream.rangeClosed(1, 10000).boxed().collect(Collectors.toMap(Function.identity(), i -> "哈哈" + i));

        map.forEach((i, j) -> {
            System.out.println("key:" + i + ",value:" + j);
        });
    }
}
