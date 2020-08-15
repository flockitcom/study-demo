package utils;

import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * 字符串分割
 */
public class UtilsTest02 {
    @Test
    public void test1() {
        String a = "dsafas";
        String[] split = a.split(",");
        for (String s : split) {
            System.out.println(s);
        }
    }

    @Test
    public void test2() {
        String a = "DEFAULT_ERROR_CODE";
        String i = a.substring(0, a.lastIndexOf('_'))+"_MSG";
        System.out.println(i);
    }
}
