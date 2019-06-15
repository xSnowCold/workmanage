package com.nefu.workmanage.controller;

import com.nefu.workmanage.entity.Exam;
import com.nefu.workmanage.entity.User;
import com.nefu.workmanage.entity.UserExam;
import com.nefu.workmanage.service.ExamService;
import com.nefu.workmanage.service.UserExamService;
import com.nefu.workmanage.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api")
public class UserExamController {
    @Autowired
    private UserService userService;
    @Autowired
    private ExamService examService;
    @Autowired
    private UserExamService userExamService;
    //分配监考人员
    @PostMapping("/manager/addUserExam/{examId}")
    public Map addUserExam(@PathVariable int examId,@RequestBody List<User> teachers){

        return Map.of("200",userExamService.addUserExam(examService.findAExam(examId),teachers));
    }
    //按照考试ID查找
    @GetMapping("/userExam/{examId}")
    public Map findById(@PathVariable int examId){
      //  return Map.of("ue",userExamService.findUEById(examId));
        List<User> userlist = new ArrayList<>();
        List<UserExam> list = userExamService.findUEById(examId);
        for(UserExam userExam:list){
            userlist.add(userExam.getUser());
        }
        return Map.of("exam",examService.findAExam(examId),"userlist",userlist);
    }
}
