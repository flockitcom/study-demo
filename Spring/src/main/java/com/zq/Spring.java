package com.zq;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zqian
 * @date 2020/12/18
 */
@SpringBootApplication
@MapperScan("com.zq.mapper")
public class Spring {
    public static void main(String[] args) {
        SpringApplication.run(Spring.class, args);
    }
}
