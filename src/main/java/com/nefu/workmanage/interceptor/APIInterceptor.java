package com.nefu.workmanage.interceptor;

import com.nefu.workmanage.entity.User;
import com.nefu.workmanage.entity.User.roles;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.nefu.workmanage.entity.User.*;

@Component
public class APIInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response
            ,Object handle) throws Exception{
        roles rid =  (roles)Enum.valueOf(roles.class,(String )(request.getSession().getAttribute("role")));
        if (rid == null){
            throw  new ResponseStatusException(HttpStatus.FORBIDDEN, "无权限");
        }else {
            return true;
        }

    }
}
