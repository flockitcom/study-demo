package com.zq;

/**
 * @author zqian
 * @date 2021/4/22
 */
public class NiuTest {
    public static void main(String[] args) {
        String str = "dsafanfvapdifiaiogfa";
        method(str, 'a', 'b');
    }

    private static void method(String str, char a, char b) {
        int count = 0;
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == a) {
                chars[i] = 'b';
                count++;
            }
        }
        System.out.println("Result String:"+String.valueOf(chars));
        System.out.println("A count:"+count);
    }
}
