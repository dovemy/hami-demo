package com.xuhaoming.hamidemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * Swagger配置类
 *
 * @author xuhaoming
 * @since 2020/6/13
 */
@Configuration
@EnableSwagger2WebMvc
public class SwaggerConfig {


    /**
     * 测试分组接口
     */
    @Bean(value = "test")
    public Docket test() {

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("测试分组")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.xuhaoming.hamidemo.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Restful API 说明文档")
                .contact("许浩明")
                .description("基于Swagger2和Knife4j实现的接口文档")
                .termsOfServiceUrl("http://localhost:8080/")
                .version("1.0")
                .build();
    }

}
