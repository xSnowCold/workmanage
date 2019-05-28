package com.nefu.workmanage.interceptor;

import com.nefu.workmanage.component.EncryptorComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private EncryptorComponent encryptorComponent;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler)throws Exception{
        Optional.ofNullable(request.getHeader("token"))
                .ifPresentOrElse(token -> {
                    var map = encryptorComponent.decrypt(token);
                    request.setAttribute("uid", map.get("uid"));
                    request.setAttribute("rid", map.get("rid"));
                }, () ->{
                    throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"未登录");
                });
        return true;
    }


}
