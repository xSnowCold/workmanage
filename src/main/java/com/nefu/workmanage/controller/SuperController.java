package com.nefu.workmanage.controller;

import com.nefu.workmanage.entity.User;
import com.nefu.workmanage.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api")
public class SuperController {
    @Autowired
    private UserService userService;

    //超管添加管理员
    @PostMapping("/super/addmanager")
    public Map addManager(@RequestBody User manager){
        manager.setRole(User.roles.MANAGER);
        return Map.of("addmanager",userService.addUser(manager));
    }

    //超管撤销管理员
    @GetMapping("/super/delmanager/{mid}")
    public Map delManager(@PathVariable int mid){
       User user =  userService.findUser(mid);
       user.setRole(User.roles.TEACHER);
       return Map.of("delmanager",userService.modifyUser(user));
    }

    //管理员列表
    @GetMapping("/super/managerList")
    public List managerList(){
        return List.of(userService.findByRole(User.roles.MANAGER));
    }
}
