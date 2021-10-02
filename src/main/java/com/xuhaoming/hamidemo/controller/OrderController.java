package com.xuhaoming.hamidemo.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xuhaoming.hamidemo.entity.HamiOrder;
import com.xuhaoming.hamidemo.service.HamiOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xuhaoming
 * @date 2021/10/2
 */
@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private HamiOrderService orderService;

    @GetMapping("page")
    public Object page() {
        Page<HamiOrder> page = orderService.page(new Page<>());
        return page;
    }


}
