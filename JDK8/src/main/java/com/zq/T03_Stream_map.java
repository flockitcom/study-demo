package com.zq;

import java.util.Arrays;
import java.util.List;

/** 
 * stream map分散操作 distinct去重
 * @author: zhangqian
 * @date: 2020/8/26
 */
public class T03_Stream_map {
    public static void main(String[] args) {
        List<String>  asList = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp", "Lispaaa","JAVA");
        asList.stream().map(String::toUpperCase).distinct().forEach(System.out::println);
    }
}
