package com.zq;

import com.zq.configuration.MainConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;

@SpringBootApplication()
@MapperScan("com.zq.mapper")
@Import(MainConfig.class) //导入主配置类（主配置文件包含其他配置类）
public class Application extends SpringBootServletInitializer {

    public static void main(String[] args) {
        new SpringApplicationBuilder(
                Application.class
        ).properties("spring.config.location=classpath:application.yml").run(args);
    }

}
