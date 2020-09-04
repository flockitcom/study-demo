package com.zq.configuration;

import com.zq.handler.MyMetaObjectHandler;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = "com.zq", useDefaultFilters = true)
@Import({MyMetaObjectHandler.class, MyBatisPlusConfig.class})
public class MainConfig {
}
