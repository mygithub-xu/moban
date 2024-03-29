package com.dhlg.utils;

import com.dhlg.module.system.sysUser.entity.SysUser;
import com.dhlg.utils.common.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * 获取当前登陆的信息
 * Created by Administrator on 2019-04-01.
 */
public class GetLoginUser {
    public static SysUser getCurrentUser() {
        Subject subject = SecurityUtils.getSubject();
        if (StringUtils.isBlank(subject)) {
            return null;
        }
        Object principal = subject.getPrincipal();
        if (StringUtils.isBlank(principal)) {
            return null;
        }
        return (SysUser) principal;
    }


    public static String getCurrentUserId() {
        SysUser currentUser = getCurrentUser();
        if(StringUtils.isBlank(currentUser)) {
            return "";
        }
        return currentUser.getId();
    }
}
