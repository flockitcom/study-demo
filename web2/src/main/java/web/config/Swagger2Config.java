package web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * restful api文档
 *
 * @author panye
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    /**
     * 公司官网
     */
    private static final String OFFICIAL_WEBSITE = "http://www.flockitcom.cn/";
    /**
     * 版权
     */
    private static final String LICENSE = "flockitcom";

    /**
     * 创建文档
     *
     * @return
     * @author panye
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("全部接口文档")
                .apiInfo(apiInfo()).select().apis(RequestHandlerSelectors.any()).paths(PathSelectors.any()).build();
    }

    /**
     * API信息
     *
     * @return
     * @author panye
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("系统API文档")
                .description("系统restful API参考手册").license("版权所有：" + LICENSE)
                .licenseUrl(OFFICIAL_WEBSITE).version("1.0").build();
    }

}