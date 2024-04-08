package com.zq.stream;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * Stream可以试用 fliter过滤,map分散操作,reduce聚合操作
 * 过滤,Predicate(and,or)
 * @author: zhangqian
 * @date: 2020/8/26
 */
public class T02_Stream_Filter {
    public static void main(String[] args) {
        List<String> languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp", "Lispaaa");

        System.out.println("Languages which starts with J :");
        filter4(languages, str -> str.startsWith("L"), s -> s.length() > 5);


    }

    public static void filter1(List<String> nameList, Predicate<String> condition) {
        for (String name : nameList) {
            if (condition.test(name)) {
                System.out.println(name + " ");
            }
        }
    }

    //改进版
    /*public static void filter2(List<String> nameList, Predicate<String> condition) {
        nameList.stream().filter(name -> condition.test(name)).forEach(n -> System.out.print(n+""));
    }*/

    //改进版
    /*public static void filter3(List<String> nameList, Predicate<String> condition) {
        nameList.stream().filter(condition::test).forEach(n -> System.out.print(n + ""));
    }*/

    //改进版
    public static void filter4(List<String> nameList, Predicate<String> condition1, Predicate<String> condition2) {
        if (condition2 != null) {
            condition1 = condition1.and(condition2);
        }
        nameList.stream().filter(condition1).forEach(n -> System.out.print(n + " "));
        System.out.println();
    }
}
