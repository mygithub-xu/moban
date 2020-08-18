package com.dhlg.shiro.realm;

import com.dhlg.module.system.sysButton.entity.SysButton;
import com.dhlg.module.system.sysButton.service.impl.SysButtonServiceImpl;
import com.dhlg.module.system.sysRole.service.impl.SysRoleServiceImpl;
import com.dhlg.module.system.sysUser.entity.SysUser;
import com.dhlg.module.system.sysUser.service.impl.SysUserServiceImpl;
import com.dhlg.utils.common.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 用户验证与授权 realm
 */
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

    /**
     * 用户调用改方法验证权限时进入此方法： SecurityUtils.getSubject();
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //获取用户
        SysUser user = (SysUser) getAvailablePrincipal(principals);

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

        //设置该用户的角色和权限数据
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //角色访问，
        authorizationInfo.setRoles(roleList);

        /*
         *  该权限必须给接口处添加@RequiresPermissions("xxx")
         */
        authorizationInfo.setStringPermissions(menuPermissionSet);

        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) {

        //判断是否为用户登入
        UsernamePasswordToken token = null;
        //instanceof检验token是否为UsernamePasswordToken的实例
        if(authenticationToken instanceof UsernamePasswordToken){
            token = (UsernamePasswordToken) authenticationToken;
        }else{
            return null;
        }
        //获取用户的输入的账号.
        String login_user = (String)token.getPrincipal();

        //根据用户名获取用户对象
        SysUser sysUser = userService.findByName(login_user);

        if(sysUser == null) {
            throw new UnknownAccountException();//没找到帐号
        }
        if(null== sysUser.getStatus()||"0".equals(sysUser.getStatus())) {
            throw new LockedAccountException(); //帐号锁定
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                sysUser, //用户
                sysUser.getPassword(), //密码
                ByteSource.Util.bytes(sysUser.getCredentialsSalt()),
                this.getName()  //realm name
        );


        return authenticationInfo;
    }
    @Override
    public boolean supports(AuthenticationToken var1){
        return var1 instanceof UsernamePasswordToken;
    }

}