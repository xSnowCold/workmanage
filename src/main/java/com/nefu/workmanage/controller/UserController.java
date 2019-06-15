package com.nefu.workmanage.controller;

import com.nefu.workmanage.entity.User;
import com.nefu.workmanage.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    //查询当前用户的基本信息
    @GetMapping("/users/user")
        public User userDetail(HttpServletRequest request){
            User user= (User) request.getSession().getAttribute("user");
            return user;
    }
    @GetMapping("/users/{id}")
    public User userDetailById(@PathVariable int id){
        User user= userService.findUser(id);
        return user;
    }

    //修改当前用户的基本信息
    @PostMapping("/users/updateuser")
    public Map updateUser(@RequestBody User user,HttpServletRequest request){
        return Map.of("newUser",userService.modifyUser(user));
    }
}
