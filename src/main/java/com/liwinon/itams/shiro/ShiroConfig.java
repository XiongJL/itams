package com.liwinon.itams.shiro;

import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // setLoginUrl 如果不设置值，默认会自动寻找Web工程根目录下的"/login.jsp"页面 或 "/login" 映射
        shiroFilterFactoryBean.setLoginUrl("/itams/login");
        // 设置无权限时跳转的 url;
        shiroFilterFactoryBean.setUnauthorizedUrl("/notRole");
        //设置退出后跳转的url
       // shiroFilterFactoryBean.setLoginUrl("/itams/datas");
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        //以下是过滤链，按顺序过滤，所以/**需要放最后
        //开放的静态资源
        filterChainDefinitionMap.put("/favicon.ico", "anon");//网站图标
        filterChainDefinitionMap.put("/itams/Ineligible", "anon");//
        filterChainDefinitionMap.put("/css/**", "anon");//
        filterChainDefinitionMap.put("/js/**", "anon");//
        filterChainDefinitionMap.put("/img/**", "anon");//
        filterChainDefinitionMap.put("/fonts/**", "anon");//
        filterChainDefinitionMap.put("/itams/api/**", "anon");//
        filterChainDefinitionMap.put("/itams/datas/**", "anon");//
        filterChainDefinitionMap.put("/itams/help/**", "anon");//
        filterChainDefinitionMap.put("/itams/register", "anon");//注册
        filterChainDefinitionMap.put("/itams/operate/getName", "anon");//获取姓名接口
        filterChainDefinitionMap.put("/itams/logout", "logout");//
        filterChainDefinitionMap.put("/itams/login", "anon");//开放登陆接口
    //    filterChainDefinitionMap.put("/itams/login", "anon");//开放登陆接口
        filterChainDefinitionMap.put("/itams/operate/**", "authc");//所有操作都需要登录
        filterChainDefinitionMap.put("/itams/upload/**", "authc");//所有上传下载都需要登录
        filterChainDefinitionMap.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }


    @Bean
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager(myRealm());

        return securityManager;
    }

    @Bean
    public CustomRealm myRealm() {
        CustomRealm myRealm = new CustomRealm();
        return myRealm;
    }

    /**
     * 缓存
     * @return
     */
//    @Bean
//    protected CacheManager cacheManager() {
//        return new MemoryConstrainedCacheManager();
//    }

}
