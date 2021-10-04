package com.xuhaoming.hamidemo.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xuhaoming
 * @date 2021/10/4
 */
@RestController
@RequestMapping("shiro")
public class ShiroController {

    @GetMapping("needRole")
    @RequiresRoles("admin")
    public String needRole() {
        return "OK";
    }

    @GetMapping("login")
    public String login(String userName, String password) {

        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(
                userName,
                password);

        SecurityUtils.getSubject().login(usernamePasswordToken);

        return "login success";
    }

    @GetMapping("logout")
    public String logout() {
        SecurityUtils.getSubject().logout();
        return "OK";
    }

    @GetMapping("user")
    public Object getUser() {
        Subject subject = SecurityUtils.getSubject();
        Object principal = subject.getPrincipal();
        return principal;
    }


}
