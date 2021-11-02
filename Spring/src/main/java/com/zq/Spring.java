package com.zq;

import com.zq.annotation.login.LoginRequiredArgumentResolver;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author zqian
 * @date 2020/12/18
 */
@SpringBootApplication
@MapperScan("com.zq.mapper")
public class Spring implements WebMvcConfigurer {
    public static void main(String[] args) {
        SpringApplication.run(Spring.class, args);
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new LoginRequiredArgumentResolver());
    }
}
