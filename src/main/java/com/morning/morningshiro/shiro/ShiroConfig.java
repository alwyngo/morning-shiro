package com.morning.morningshiro.shiro;

import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
public class ShiroConfig {

    @Autowired
    private CustomizeSessionManager customizeSessionManager;

    @Resource
    private CustomizeSessionDao customizeSessionDao;

    @Autowired
    private CustomizeCacheManager customizeCacheManager;

    @Bean
    public Realm realm(){
        return new CustomizeRealm();
    }

    @Bean
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager(realm());
        defaultWebSecurityManager.setSessionManager(sessionManager());
        defaultWebSecurityManager.setCacheManager(cacheManager());
        defaultWebSecurityManager.setRealm(realm());
        return defaultWebSecurityManager;
    }

    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();

        // logged in users with the 'admin' role
        chainDefinition.addPathDefinition("/admin/**", "authc, roles[admin]");

        // logged in users with the 'document:read' permission
        chainDefinition.addPathDefinition("/docs/**", "authc, perms[document:read]");

        chainDefinition.addPathDefinition("/login/**", "anon");

        // all other paths require a logged in user
        chainDefinition.addPathDefinition("/**", "user");
        return chainDefinition;
    }

    @Bean
    public CacheManager cacheManager() {
        return customizeCacheManager;
    }

    @Bean
    public SessionManager sessionManager() {
        customizeSessionManager.setSessionDAO(customizeSessionDao);
        customizeSessionManager.setSessionIdUrlRewritingEnabled(false);
        return customizeSessionManager;
    }
}
