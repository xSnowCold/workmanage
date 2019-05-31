package com.nefu.workmanage.interceptor;

import ch.qos.logback.core.net.SyslogOutputStream;
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
        HttpSession session = request.getSession(false);
        if(session!=null){
            System.out.println(session.getId());
            System.out.println(session.getAttribute("role"));
            return true;
        }else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"未登录");
        }
        /*System.out.println((String)request.getSession(false).getAttribute("token"));

        Optional.ofNullable((String)(request.getSession().getAttribute("token")))
                .ifPresentOrElse(token -> {
                    var map = encryptorComponent.decrypt(token);
                    request.getSession().setAttribute("uid", map.get("uid"));
                    request.getSession().setAttribute("rid", map.get("rid"));
                }, () ->{
                    throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"未登录");
                });
        return true;*/
    }


}
