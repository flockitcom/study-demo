package utils;

import org.junit.Test;

/**
 * 两数交换
 */
public class UtilsTest03 {
    @Test
    public void test1() {
        int a = -9;
        int b = -32;
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        System.out.println(a);
        System.out.println(b);
    }

    @Test
    public void test2(){
        String s2 = new String("Programming");
        String s1 = "Programming";
        String s3 = "Program";
        String s4 = "ming";
        String s5 = "Program" + "ming";
        String s6 = s3 + s4;
        System.out.println(s1 == s2); //false
        System.out.println(s1 == s5); //true
        System.out.println(s1 == s6); //false
        System.out.println(s5 == s6); //false4
        System.out.println(s1 == s6.intern()); //true
        System.out.println(s2 == s2.intern()); //false
    }

}
