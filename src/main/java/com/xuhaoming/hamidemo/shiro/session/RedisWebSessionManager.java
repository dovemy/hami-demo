package com.xuhaoming.hamidemo.shiro.session;

import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
 * Shiro Web会话管理器Redis实现
 *
 * @author xuhaoming
 * @date 2021/10/5
 */
public class RedisWebSessionManager extends DefaultWebSessionManager {

    /**
     * 自定义SessionIdName
     *
     * {@link org.apache.shiro.web.servlet.ShiroHttpSession#DEFAULT_SESSION_ID_NAME}
     */
    public static final String SESSION_ID_NAME = "SESSION_ID";

    public RedisWebSessionManager(RedisSessionDAO redisSessionDAO) {
        super();
        // Cookie键改为自定义键
        super.getSessionIdCookie().setName(SESSION_ID_NAME);
        super.setSessionDAO(redisSessionDAO);
    }


    /**
     * 获取SessionId：依次从cookie、uri参数、query参数取值，扩展了从header中取值
     */
    @Override
    public Serializable getSessionId(ServletRequest request, ServletResponse response) {
        Serializable sessionIdByCookie = super.getSessionId(request, response);
        if (sessionIdByCookie != null) {
            return sessionIdByCookie;
        }
        if (request instanceof HttpServletRequest) {
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            return httpServletRequest.getHeader(SESSION_ID_NAME);
        }
        return null;
    }
}
