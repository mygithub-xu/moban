package com.dhlg.config;

import com.dhlg.filter.JwtFilter;
import com.dhlg.shiro.realm.UserRealm;

import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;

import org.crazycake.shiro.IRedisManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.hibernate.JDBCException;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.mgt.SecurityManager;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;


import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.Filter;
/**
 * 描述
 * User:whfch
 * Date:2020/2/8
 * Time:15:59
 */
@Configuration
public class shiroConfig {

    @Value("${spring.redis.host}")
    private String redis_host;

    @Value("${spring.redis.port}")
    private Integer redis_port;

    @Value("${spring.redis.timeout}")
    private Integer redis_timeout;

    @Value("${spring.redis.password}")
    private String redis_password;

    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager)throws JDBCException{
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 必须设置 SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        //配置拦截器
        Map<String, String> map = new LinkedHashMap<String, String>();
        // 配置不会被拦截的链接 顺序判断
        map.put("/static/**", "anon");
        //设置不被拦截的api
        map.put("/api/system/sysUser/login", "anon");
        map.put("/api/system/sysUser/logout", "anon");
        map.put("/api/system/sysUser/getCode/*","anon");
        map.put("/api/test/**","anon");
        map.put("/druid/**","anon");
        map.put("/swagger-ui.html","anon");
        map.put("/webjars/**","anon");
        map.put("/swagger-resources/**","anon");


        //自定义拦截器
        Map<String, Filter> filtersMap = new LinkedHashMap<String, Filter>();
        filtersMap.put("jwt", new JwtFilter());
        shiroFilterFactoryBean.setFilters(filtersMap);
        map.put("/**", "jwt");
//        shiroFilterFactoryBean.setLoginUrl("/login.html");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }


    /**
     * 安全管理器，管理所有Subject，可以配合内部安全组件，是shrio核心
     * http://shiro.apache.org/architecture.html其官网有详细的介绍
     * @return
     */
    @Bean("securityManager")
    public DefaultWebSecurityManager securityManager(UserRealm userRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //配置realm
        securityManager.setRealm(userRealm);

        //关闭shrio
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
        securityManager.setSubjectDAO(subjectDAO);

        //缓存权限等数据
        securityManager.setCacheManager(redisCacheManager());

        return securityManager;
    }

    /**
     * shiro缓存管理器;cache
     * @return
     */
    @Bean
    public RedisCacheManager redisCacheManager() {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        redisCacheManager.setPrincipalIdFieldName("id");
        //单位秒：暂时定为2小时
        redisCacheManager.setExpire(2*60*60);
        return redisCacheManager;
    }

    @Bean
    public IRedisManager redisManager(){
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(redis_host);
        redisManager.setPort(redis_port);
        redisManager.setPassword(redis_password);
        redisManager.setTimeout(redis_timeout);
        return redisManager;
    }

    /*
     * 开启shiro 注解模式
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor =
                new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    /**
     * LifecycleBeanPostProcessor，这是个DestructionAwareBeanPostProcessor的子类，
     * 负责org.apache.shiro.util.Initializable类型bean的生命周期的，初始化和销毁。
     * 主要是AuthorizingRealm类的子类，以及EhCacheManager类。
     * static是为了不影响@Value("${xxx}")取不到值
     */
    @Bean
    public static LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }


    /**
     * 解决该页面无法获取yml配置的一些属性
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
