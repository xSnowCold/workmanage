package com.nefu.workmanage.controller;

import com.nefu.workmanage.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api")
public class TeacherController {
    @Autowired
    private UserService userService;

    //获取当前用户的监考信息
}
