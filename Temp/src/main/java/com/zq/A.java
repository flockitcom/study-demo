package com.zq;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author zqian
 * @date 2021/1/25
 */
public class A {
    public static void main(String[] args) {
        for (int i = 0; i < 100; ++i) {
            System.out.println(i);
        }
    }

    private void out() {
        Employee e1 = new Employee("John", 25, 3000, 9922001);
        Employee e2 = new Employee("Ace", 22, 2000, 5924001);
        Employee e3 = new Employee("Keith", 35, 4000, 3924401);

        List<Employee> employees = new ArrayList<>();
        employees.add(e1);
        employees.add(e2);
        employees.add(e3);

//        employees.sort((o1,o2)->o1.getName().compareTo(o2.getName()));
//        employees.sort(Comparator.comparing(Employee::getNo).reversed());
        List collect = employees.stream().filter(s -> StringUtils.isNotBlank(s.getName())).sorted(Comparator.comparing(Employee::getName).reversed()).collect(Collectors.toList()); System.out.println(collect);
        employees.forEach(System.out::println);
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    class Employee{
        private String name;
        private Integer age;
        private Integer money;
        private Integer no;
    }


}
