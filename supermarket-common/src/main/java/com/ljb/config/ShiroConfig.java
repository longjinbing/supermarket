package com.ljb.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.ljb.redis.ShiroCacheManager;
import com.ljb.shiro.UserRealm;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.mgt.RememberMeManager;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;
import org.apache.shiro.session.mgt.quartz.QuartzSessionValidationScheduler;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.util.HashMap;
import java.util.Map;

/**
 * 作者: @author longjinbin <br>
 * 时间: 2018/9/13<br>
 * 描述: <br>
 */
@Configuration
public class ShiroConfig {
    @Bean
    public UserRealm realm() {// 1、获取配置的Realm，之所以没使用注解配置，是因为此处需要考虑到加密处理
        UserRealm realm = new UserRealm();
        //realm.setCacheManager(shiroCacheManager());
        realm.setCachingEnabled(true);
        return realm;
    }

    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator daap = new DefaultAdvisorAutoProxyCreator();
        daap.setProxyTargetClass(true);
        return daap;
    }

    @Bean
    public CacheManager shiroCacheManager(){
        ShiroCacheManager cacheManager=new ShiroCacheManager();
        return cacheManager;
    }

    // 配置此bean才能结合thymeleaf使用shiro页面标签语法
    @Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }

    @Bean
    public SessionIdGenerator sessionIdGenerator() {
        return new JavaUuidSessionIdGenerator();
    }

    @Bean
    public SessionDAO sessionDAO() {
        EnterpriseCacheSessionDAO sessionDAO = new EnterpriseCacheSessionDAO();
        sessionDAO.setActiveSessionsCacheName("shiro-activeSessionCache");
        sessionDAO.setSessionIdGenerator(sessionIdGenerator());
        return sessionDAO;
    }

    @Bean
    public QuartzSessionValidationScheduler quartzSessionValidationScheduler() {
        QuartzSessionValidationScheduler sessionValidationScheduler = new QuartzSessionValidationScheduler();
        sessionValidationScheduler.setSessionValidationInterval(100000);
        return sessionValidationScheduler;
    }

    @Bean
    public RememberMeManager rememberManager() {
        CookieRememberMeManager rememberMeManager = new CookieRememberMeManager();
        SimpleCookie cookie = new SimpleCookie("supermarket-remember");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(3600);
        rememberMeManager.setCookie(cookie);
        return rememberMeManager;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager());
        return authorizationAttributeSourceAdvisor;
    }

    @Bean
    public DefaultWebSessionManager sessionManager() { // 6
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setGlobalSessionTimeout(1000000);
        sessionManager.setDeleteInvalidSessions(true);
        //sessionManager.setCacheManager(shiroCacheManager());
        sessionManager.setSessionValidationSchedulerEnabled(true);
        sessionManager.setSessionDAO(sessionDAO());
        SimpleCookie sessionIdCookie = new SimpleCookie("supermarket");
        sessionIdCookie.setHttpOnly(true);
        sessionIdCookie.setMaxAge(-1);
        sessionManager.setSessionIdCookie(sessionIdCookie);
        sessionManager.setSessionIdCookieEnabled(true);
        return sessionManager;
    }

    @Bean
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //securityManager.setCacheManager(shiroCacheManager());
        securityManager.setRealm(realm());
        securityManager.setSessionManager(sessionManager());
        securityManager.setRememberMeManager(rememberManager());
        return securityManager;
    }

    @Bean
    public LogoutFilter logoutFilter() { // 在ShiroFilterFactoryBean中使用
        LogoutFilter logoutFilter = new LogoutFilter();
        logoutFilter.setRedirectUrl("/account/login"); // 首页路径，登录注销后回到的页面
        return logoutFilter;
    }

    //shiro 生命周期处理器
    @Bean
    public LifecycleBeanPostProcessor LifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilter() {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 必须设置 SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager());
        shiroFilterFactoryBean.setLoginUrl("/account/login"); // 设置登录页路径
        shiroFilterFactoryBean.setSuccessUrl("/"); // 设置跳转成功页
        shiroFilterFactoryBean.setUnauthorizedUrl("/account/unauth"); // 授权错误页
        /*Map<String, Filter> filters = new HashMap<String, Filter>();
        *//*filters.put("authc", this.getLoginFilter());
        filters.put("logout", logoutFilter());*//*
        //添加获取当前登录用户filter
        //filters.put("sysUser", getSysUserFilterConfig());
        // 登录并发拦截
        //filters.put("kickout", this.getKickoutSessionControlFilter(securityManager.getSessionManager()));
        shiroFilterFactoryBean.setFilters(filters);*/
        Map<String, String> filterChainDefinitionMap = new HashMap<String, String>();
        filterChainDefinitionMap.put("/static/**", "anon");
        filterChainDefinitionMap.put("/store/**", "anon");
        filterChainDefinitionMap.put("/common/**", "anon");
        filterChainDefinitionMap.put("/storecart/**", "anon");
        filterChainDefinitionMap.put("/account/**", "anon");
        filterChainDefinitionMap.put("/**", "authc");// 请求拦截路径
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }
}