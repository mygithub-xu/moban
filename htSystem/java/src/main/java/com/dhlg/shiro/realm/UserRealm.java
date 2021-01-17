package com.dhlg.shiro.realm;

import com.dhlg.shiro.token.JwtToken;
import com.dhlg.utils.Dictionaries;
import com.dhlg.utils.JwtUtil;
import com.dhlg.module.system.sysButton.entity.SysButton;
import com.dhlg.module.system.sysButton.service.impl.SysButtonServiceImpl;
import com.dhlg.module.system.sysRole.service.impl.SysRoleServiceImpl;
import com.dhlg.module.system.sysUser.entity.SysUser;
import com.dhlg.module.system.sysUser.service.impl.SysUserServiceImpl;
import com.dhlg.utils.StringUtils;
import com.dhlg.redis.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 用户验证与授权 realm
 */
@Component
@Slf4j
public class UserRealm extends AuthorizingRealm {

    @Autowired
    @Lazy
    private SysUserServiceImpl userService;

    @Autowired
    @Lazy
    private SysRoleServiceImpl roleService;

    @Autowired
    @Lazy
    private SysButtonServiceImpl buttonService;

    @Autowired
    @Lazy
    private RedisUtil redisUtil;

    /**
     * 必须重写此方法，不然Shiro会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    /**
     * 用户调用改方法验证权限时进入此方法： SecurityUtils.getSubject();
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //获取用户
        SysUser user = (SysUser) getAvailablePrincipal(principals);
        //配置用户权限
        Set<String> roleList = new HashSet<>();
        Set<String> menuPermissionSet = new HashSet<>();
        List<SysButton> buttonList = null;
        //从数据库查询该用户的角色和权限数据
        if("admin".equals(user.getUserName())){
            //超级管理员，具有所有权限
            buttonList = buttonService.findAllButtonUrl();

        }else{
            //不是超级管理员
            roleList= roleService.findRoles(user.getRoleIds());
            buttonList= buttonService.findButtonUrl(user.getRoleIds());
        }
        if (!StringUtils.isBlank(buttonList)){
            for(SysButton sysMenu:buttonList){
                if (!StringUtils.isBlank(sysMenu.getButtonUrl())){
                    menuPermissionSet.add(sysMenu.getButtonUrl());
                }
            }
        }
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //角色权限
        authorizationInfo.setRoles(roleList);
        // 该权限必须给接口处添加@RequiresPermissions("xxx")
        authorizationInfo.setStringPermissions(menuPermissionSet);

        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) {
        String token = (String) auth.getCredentials();
        //instanceof检验token是否为UsernamePasswordToken的实例
        if (token == null) {
            throw new AuthenticationException("token为空!");
        }

        // 校验token有效性
        SysUser loginUser = this.checkUserTokenIsEffect(token);

        return new SimpleAuthenticationInfo(
                loginUser, //用户
                token,
                getName()  //realm name
        );
    }

    private SysUser checkUserTokenIsEffect(String token) {
        //获取用户的输入的账号
        String login_user = JwtUtil.getUsername(token);
        if (login_user == null) {
            throw new AuthenticationException("token非法无效!");
        }

        // 获取用户信息
        SysUser sysUser  = (SysUser)redisUtil.get(Dictionaries.PREFIX_USER_ + token);

        if (sysUser == null) {
            sysUser = userService.findByName(login_user);
        }

        if (sysUser == null) {
            throw new AuthenticationException("用户不存在!");
        }

        // 刷新token
        if (!jwtTokenRefresh(token, login_user, sysUser.getPassword())) {
            throw new AuthenticationException("Token失效请重新登录!");
        }
        if(null== sysUser.getStatus()||"0".equals(sysUser.getStatus())) {
            throw new LockedAccountException(); //帐号锁定
        }

        return sysUser;
    }

    //设置redis保存token的时间为jwt失效时间的两倍，然后当token失效时重新赋值，这样就能保证token的刷新了。
    private boolean jwtTokenRefresh(String token, String login_user, String password) {
        //找到PREFIX_USER_TOKEN+token的值,当其不存在，那就证明其完全失效。
        String cacheToken = String.valueOf(redisUtil.get(Dictionaries.PREFIX_USER_TOKEN + token));
        if(!StringUtils.isBlank(cacheToken)){
            //检验token的值是否在有效缓存内，，没有则更新。
            if (!JwtUtil.verify(cacheToken, login_user, password)) {
                //生成jwt的token。
                String newAuthorization = JwtUtil.sign(login_user, password);
                redisUtil.set(Dictionaries.PREFIX_USER_TOKEN + token, newAuthorization);
                // 设置超时时间为两倍jwt失效时间
                redisUtil.expire(Dictionaries.PREFIX_USER_TOKEN + token, JwtUtil.EXPIRE_TIME * 2 / 1000);
            }
            return true;
        }
        return false;
    }

}