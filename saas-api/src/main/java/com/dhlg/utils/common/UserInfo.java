package com.dhlg.utils.common;

import com.dhlg.module.system.sysUser.entity.SysUser;
import com.dhlg.redis.RedisUtil;
import com.dhlg.utils.Dictionaries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class UserInfo {

    @Autowired
    private RedisUtil redisUtil;

    public String getToken(){
        return HttpContextUtils.getRequestHeader(Dictionaries.ACCESS_TOKEN);
    }

    public SysUser getUser(){
        String token = getToken();
        if (StringUtils.isBlank(token)){
            return null;
        }
        return (SysUser)redisUtil.get(Dictionaries.PREFIX_USER_ + token);
    }

    public String getUserID(){
        SysUser user = getUser();
        if (StringUtils.isBlank(user)){
            return null;
        }
        return user.getId();
    }

    public String getUserName(){
        SysUser user = getUser();
        if (StringUtils.isBlank(user)){
            return null;
        }
        return user.getLoginUser();
    }
}
