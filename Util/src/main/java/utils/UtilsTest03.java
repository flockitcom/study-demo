package utils;

import org.junit.Test;

/**
 * 两数交换
 */
public class UtilsTest03 {
    @Test
    public void test1() {
        int a = 9;
        int b = 32;
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        System.out.println(a);
        System.out.println(b);
    }

}
