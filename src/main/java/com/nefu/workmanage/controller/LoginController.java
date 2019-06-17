package com.nefu.workmanage.controller;

import com.nefu.workmanage.component.EncryptorComponent;
import com.nefu.workmanage.entity.User;
import com.nefu.workmanage.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api")
public class LoginController {
    private static final String TEACHER_ROLE = "bb63e5f7e0f2ffae845c";
    private static final String MANAGER_ROLE = "6983f953b49c88210cb9";
    private static final String SUPER_ROLE = "tyh3f953949c88210cb9";
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private EncryptorComponent encryptorComponent;

    private HttpSession session;
    @PostMapping("/login")
    public Map login(@RequestBody User user, HttpServletRequest request){
        session = request.getSession();
        User dbUser = userService.findByAccount(user.getAccount());
        if(dbUser != null){
            if(!passwordEncoder.matches(user.getPassword(),dbUser.getPassword())){
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "用户名或密码错误");
            }else {

                session.setAttribute("user", dbUser);
                session.setAttribute("role", dbUser.getRole());
                return Map.of("currentAuthority",dbUser.getRole(),"status","ok","type",dbUser.getAccount());
            }
        }else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "用户名或密码错误");
        }
        /*Optional.ofNullable(userService.findByAccount(user.getAccount()))
                .ifPresentOrElse(u -> {
                    if(!passwordEncoder.matches(user.getPassword(),u.getPassword())){
                        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "用户名或密码错误");
                    }
                    *//*Map map = Map.of("uid",u.getId(),"rid",u.getRole());
                    String token = encryptorComponent.encrypt(map);*//*
                    //session.setAttribute("user", map);
                    request.getSession().setAttribute("user", u);
                    request.getSession().setAttribute("role", u.getRole());

                    System.out.println();


                    Map.of("role",u.getRole());
                   // response.setHeader("token", token);
                   *//* String role = null;
                    if(u.getRole() == User.roles.TEACHER){
                        role = TEACHER_ROLE;
                    }
                    if(u.getRole() == User.roles.MANAGER){
                        role = MANAGER_ROLE;
                    }
                    if (u.getRole() == User.roles.SUPER){
                        role = SUPER_ROLE;
                    }*//*
                    //request.getSession().setAttribute("role", role);
                   // response.setHeader("role", role);
                },() -> {
                    throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "用户名或密码错误");
                });*/

    }
}
