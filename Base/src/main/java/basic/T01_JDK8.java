package basic;

import java.util.Arrays;
import java.util.List;

/**
 * ::的使用
 */
public class T01_JDK8 {
    static void test(String abc) {
        System.out.println(abc);
    }

    public static void main(String[] args) {
        List<String> arr = Arrays.asList("abdf", "bdsfesagg", "asgdfsgdfgd");
        arr.forEach(T01_JDK8::test);
    }
}
