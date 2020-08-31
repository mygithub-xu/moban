package com.dhlg.filter;

import org.apache.shiro.web.filter.authc.UserFilter;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

@Component
public class CheckUserStatusFilter extends UserFilter{

    protected boolean onAccessDenied(ServletRequest request, ServletResponse response){
        return true;
    }
}
