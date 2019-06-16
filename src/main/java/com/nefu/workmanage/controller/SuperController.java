package com.nefu.workmanage.controller;

import com.nefu.workmanage.entity.User;
import com.nefu.workmanage.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api")
public class SuperController {
    @Autowired
    private UserService userService;

    //超管添加管理员
    @GetMapping("/super/addmanager/{id}")
    public void addManager(@PathVariable int id, HttpServletResponse response){
        User teacher = userService.findUser(id);
        teacher.setRole(User.roles.MANAGER);
        userService.modifyUser(teacher);
        /*try {
            response.sendRedirect("/api/super/managerList");
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    //超管撤销管理员
    @GetMapping("/super/delmanager/{id}")
    public void delManager(@PathVariable int id,HttpServletResponse response){
       User user =  userService.findUser(id);
       user.setRole(User.roles.TEACHER);
        userService.modifyUser(user);
        /*try {
            response.sendRedirect("/api/super/managerList");
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    //管理员列表
    @GetMapping("/super/managerList")
    public Map managerList(){
        List list = userService.findByRole(User.roles.MANAGER);
        list.addAll(userService.findByRole(User.roles.TEACHER));

        return Map.of("list",list,"pagination",Map.of("current",1,"pageSize",10,"total",list.size()));
    }
}
