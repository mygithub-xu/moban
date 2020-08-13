package com.dhlg.config;

import com.dhlg.utils.shiro.realm.UserRealm;
import com.dhlg.utils.shiro.session.ShiroSessionManager;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.hibernate.JDBCException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.mgt.SecurityManager;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;


import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
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

    @Bean
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager)throws JDBCException{
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 必须设置 SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl("/login.html");

        // 过滤链定义，从上向下顺序执行，一般将 /**放在最为下边 :这是一个坑呢，一不小心代码就不好使了;
        // ① authc:所有url都必须认证通过才可以访问; ② anon:所有url都都可以匿名访问
        //自定义拦截器
        Map<String, Filter> filtersMap = new LinkedHashMap<String, Filter>();
        shiroFilterFactoryBean.setFilters(filtersMap);

        //配置拦截器
        Map<String, String> map = new LinkedHashMap<String, String>();
        // 配置不会被拦截的链接 顺序判断
        //必须
        map.put("/static/**", "anon");
        //设置所有api的访问接口都不被拦截
        map.put("/moban/**", "anon");
        map.put("/druid/**","anon");
        map.put("/api/**", "anon");
//        map.put("/**", "authc,kickout");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }

    /**
     * 凭证匹配器，与自定义的密码加密要保持一致
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");//散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashIterations(2);//散列的次数，比如散列2次，相当于 md5(md5(""));
        return hashedCredentialsMatcher;
    }
    /**
     * 身份认证realm,及用户权限数据的注入
     * @return
     */
    @Bean
    public UserRealm userRealm() {
        UserRealm userRealm = new UserRealm();
        //启用身份验证缓存，数据就会进入redis
        userRealm.setCachingEnabled(true);//允许缓存
        userRealm.setCredentialsMatcher(hashedCredentialsMatcher());//设置其加密方式
        userRealm.setAuthorizationCachingEnabled(true);//允许授权缓存
        return userRealm;
    }

    /**
     * 安全管理器，管理所有Subject，可以配合内部安全组件，是shrio核心
     * http://shiro.apache.org/architecture.html其官网有详细的介绍
     * @return
     */
    @Bean
    public SecurityManager securityManager(UserRealm userRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();

        securityManager.setRealm(userRealm);
        //自定义缓存管理
        securityManager.setCacheManager(redisCacheManager());
        //自定义session管理
        securityManager.setSessionManager(sessionManager());
        //注入记住我管理器;
//        securityManager.setRememberMeManager(rememberMeManager());
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
        //权限缓存，单位秒 测试后：当权限缓存过期，token没过期，会继续从数据库拿数据。所以这里时间需设置的比会话缓存大
        redisCacheManager.setExpire(redis_timeout);
        return redisCacheManager;
    }

    @Bean
    public RedisManager redisManager(){
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(redis_host);
        redisManager.setPort(redis_port);
        redisManager.setPassword(redis_password);
        return redisManager;
    }

    //添加bean
    /**
     * 自定义sessionManager
     * @return
     */
    @Bean
    public DefaultWebSessionManager sessionManager() {

        //前后端分离而自定义sessionManager
        ShiroSessionManager manager=new ShiroSessionManager();

        //此处使用的是redis缓存
        manager.setSessionDAO(redisSessionDAO());

        //会话时间单位毫秒
        manager.setGlobalSessionTimeout(redis_timeout*1000);

        //删除无效session对象
        manager.setDeleteInvalidSessions(true);
        //是否开启定时调度器进行检测过期session 默认为true，定时检测时间为1秒
        manager.setSessionValidationSchedulerEnabled(true);
        manager.setSessionValidationInterval(1000);
        return manager;
    }
    /**
     * redisSessionDAO
     */
    public RedisSessionDAO redisSessionDAO() {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        return redisSessionDAO;
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
