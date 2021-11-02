package com.zq;


import lombok.Data;
import org.junit.Test;
import org.springframework.beans.BeanUtils;


/**
 * @author zqian
 * @date 2021/4/2
 */
public class BeanUtilsTest {
    @Test
    public void copy() {
        Person person = new Person();
        person.setName("aaa");
        person.setAge(15);

        Man man = new Man();
        System.out.println(man);
    }

    @Data
    class Person {
        private String name;
        private Integer age;
    }

    @Data
    class Man{
        private String name;
        private Integer height;
    }
}
