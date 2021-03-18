package hutool.core;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.TypeReference;
import org.junit.Test;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * 类型转化类
 * @author zqian
 * @date 2021/1/25
 */
public class ConvertDemo {
    /**
     * 转换为字符串：
     */
    @Test
    public void convertStr() {
        int a = 1;
        //aStr为"1"
        String aStr = Convert.toStr(a);
        System.out.println(aStr);

        long[] b = {1, 2, 3, 4, 5};
        //bStr为："[1, 2, 3, 4, 5]"
        String bStr = Convert.toStr(b);
        System.out.println(bStr);
    }

    /**
     * 转换数组
     */
    @Test
    public void convertArray() {
        String[] b = {"1", "2", "3", "4"};
        //结果为Integer数组
        Integer[] intArray = Convert.toIntArray(b);
        System.out.println(Arrays.toString(intArray));

        long[] c = {1, 2, 3, 4, 5};
        //结果为Integer数组
        Integer[] intArray2 = Convert.toIntArray(c);
        System.out.println(Arrays.toString(intArray2));
    }

    /**
     * 转换日期
     */
    @Test
    public void convertDate() {
        String a = "2017-05-06";
        Date value = Convert.toDate(a);
    }

    /**
     * 转换集合
     */
    @Test
    public void convertCollection() {
        Object[] a = {"a", "你", "好", "", 1};
        //从4.1.11开始可以这么用
        List<String> list = Convert.toList(String.class,a);
        list.forEach(System.out::println);
    }

    /**
     * 任意类型转换
     */
    @Test
    public void convertAny() {
        Object[] a = {"a", "你", "好", "", 1};
        List convert = Convert.convert(List.class, a);
        convert.forEach(System.out::println);
    }

    /**
     * 泛型转换
     */
    @Test
    public void convertGeneric() {
        Object[] a = {"a", "你", "好", "", 1};
        List<String> convert = Convert.convert(new TypeReference<List<String>>() {}, a);
        convert.forEach(System.out::println);
    }

}
