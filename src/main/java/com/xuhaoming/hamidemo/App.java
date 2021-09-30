package com.xuhaoming.hamidemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author xuhaoming
 * @date 2021/9/30 11:37
 */
@SpringBootApplication(scanBasePackages = {"com.xuhaoming.hami.web", "com.xuhaoming.hamidemo"})
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

}
