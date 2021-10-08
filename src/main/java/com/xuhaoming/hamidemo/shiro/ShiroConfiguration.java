package com.xuhaoming.hamidemo.shiro;

import com.xuhaoming.hamidemo.shiro.session.RedisSessionDAO;
import com.xuhaoming.hamidemo.shiro.session.RedisWebSessionManager;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Shiro核心配置类
 *
 * @author xuhaoming
 * @date 2021/10/4
 */
@Configuration
public class ShiroConfiguration {


    /**
     * Shiro过滤工程Bean
     *
     * 默认过滤器参见{@link org.apache.shiro.web.filter.mgt.DefaultFilter}
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String, Filter> filters = shiroFilterFactoryBean.getFilters();
        // 自定义filter不能注册为SpringBean，否则会被当做Spring中的filter全局加载，导致下列配置失效
        filters.put("authc", new HamiAuthenticationFilter());

        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        // 配置不会被拦截的链接 顺序判断 注意此处无须加上context-path路径
        // authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问
        filterChainDefinitionMap.put("/static/**", "anon");
        filterChainDefinitionMap.put("/swagger-ui.html", "anon");
        filterChainDefinitionMap.put("/swagger-resources/**", "anon");
        filterChainDefinitionMap.put("/v2/api-docs", "anon");
        filterChainDefinitionMap.put("/v2/api-docs-ext", "anon");
        filterChainDefinitionMap.put("/webjars/**", "anon");
        filterChainDefinitionMap.put("/doc.html", "anon");
        filterChainDefinitionMap.put("/favicon.ico", "anon");
        filterChainDefinitionMap.put("/shiro/login", "anon");
        filterChainDefinitionMap.put("/shiro/logout", "logout");
        filterChainDefinitionMap.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    @Bean(name = "zeusAuthRealm")
    public HamiAuthRealm zeusAuthRealm(/*HashedCredentialsMatcher matcher*/) {
        HamiAuthRealm hamiAuthRealm = new HamiAuthRealm();
        hamiAuthRealm.setCacheManager(new MemoryConstrainedCacheManager());
//        hamiAuthRealm.setCredentialsMatcher(matcher);
        return hamiAuthRealm;
    }

    @Bean
    public SecurityManager securityManager(RedisWebSessionManager redisWebSessionManager) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(zeusAuthRealm());
        securityManager.setSessionManager(redisWebSessionManager);
        return securityManager;
    }


//    @Bean
//    public SessionManager sessionManager(RedisWebSessionManager redisWebSessionManager) {
////        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
////        sessionManager.getSessionIdCookie().setName(SESSION_ID_NAME);
////        sessionManager.setSessionDAO(redisSessionDAO);
//        return redisWebSessionManager;
//    }

    @Bean
//    @ConditionalOnBean(RedisSessionDAO.class)
//    @ConditionalOnMissingBean(DefaultWebSessionManager.class)
    public RedisWebSessionManager redisWebSessionManager(RedisSessionDAO redisSessionDAO) {
        return new RedisWebSessionManager(redisSessionDAO);
    }

    @Bean
//    @ConditionalOnMissingBean(AbstractSessionDAO.class)
    public RedisSessionDAO redisSessionDAO() {
        return new RedisSessionDAO();
    }

    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        return new HashedCredentialsMatcher();
    }

//    @Bean(name = "hashedCredentialsMatcher")
//    public ZeusHashedCredentialsMatcher hashedCredentialsMatcher() {
//        ZeusHashedCredentialsMatcher hashedCredentialsMatcher = new ZeusHashedCredentialsMatcher();
//        // 采用MD5方式加密
//        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
//        // 设置加密次数
//        hashedCredentialsMatcher.setHashIterations(1);
//        return hashedCredentialsMatcher;
//    }


    @Bean
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }


}
