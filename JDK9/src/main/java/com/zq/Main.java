package com.zq;

import java.lang.reflect.Field;

/**
 * main
 *
 * @author zhangqian
 */
public class Main {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Class<String> stringClass = String.class;
        Field field = stringClass.getDeclaredField("value");
        field.setAccessible(true);
        System.out.println(field.get("ABCD"));
    }

}
