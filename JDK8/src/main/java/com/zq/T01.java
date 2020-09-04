package com.zq;

import java.util.Arrays;
import java.util.List;

/**
 * 打印
 * @author: zhangqian
 * @date: 2020/8/26
 */
public class T01 {
    public static void main(String[] args) {
        List<String> asList = Arrays.asList("afafas", "agasgasg", "gdfgvdzgvsa");
        asList.forEach(n -> System.out.println(n));

        asList.forEach(System.out::println);
    }
}
