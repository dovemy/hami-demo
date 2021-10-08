package com.xuhaoming.hamidemo.controller;

import io.github.dovemy.hami.web.exception.BusinessException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试控制器
 *
 * @author xuhaoming
 * @date 2021/9/30 14:01
 */
@RestController
@RequestMapping("test")
public class TestController {

    @GetMapping
    public String test(Integer a) {
        if (a != null) {
            BusinessException error = new BusinessException("error");
            throw error;
        }
        return "OK";
    }

}
