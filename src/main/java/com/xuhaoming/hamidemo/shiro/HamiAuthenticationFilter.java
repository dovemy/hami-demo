package com.xuhaoming.hamidemo.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * 自定义认证过滤器
 *
 * @author xuhaoming
 * @date 2021/1/17
 */
public class HamiAuthenticationFilter extends FormAuthenticationFilter {

    /**
     * 替换Shiro未登录自动重定向到Web工程根目录下的"/login.jsp"页面实现
     */
    @Override
    protected void redirectToLogin(ServletRequest request, ServletResponse response) throws IOException {
        System.out.println("redirectToLogin");
        throw new AuthenticationException();
//        response.setContentType("application/json; charset=utf-8");
        // 自定义返回内容
//        response.getWriter().write(JSON.toJSONString(Response.fail(ErrorCodeEnum.UNAUTHORIZED.getCode(), ErrorCodeEnum.UNAUTHORIZED.getMessage())));
    }

}
