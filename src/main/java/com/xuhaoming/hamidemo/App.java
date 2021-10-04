package com.xuhaoming.hamidemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author xuhaoming
 * @date 2021/9/30 11:37
 */
@MapperScan("com.xuhaoming.hamidemo.mapper")
@SpringBootApplication(scanBasePackages = {"io.github.dovemy.hami.web", "com.xuhaoming.hamidemo"})
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

}
