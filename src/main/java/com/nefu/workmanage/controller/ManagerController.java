package com.nefu.workmanage.controller;

import com.nefu.workmanage.entity.Exam;
import com.nefu.workmanage.entity.User;
import com.nefu.workmanage.service.ExamService;
import com.nefu.workmanage.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.http.HttpClient;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api")
public class ManagerController {
    @Autowired
    private UserService userService;
    @Autowired
    private ExamService examService;
    //修改考试信息
    @PostMapping("/manager/modifyExam")
    public void modifyExam(@RequestBody Exam exam, HttpServletResponse response, HttpServletRequest request){
         examService.modifyExam(exam);
        try {
            response.sendRedirect("/api/exams");
        } catch (IOException e) {
            e.printStackTrace();
        }
        ;
    }
    //管理员添加教师
    @PostMapping("/manager/addteacher")
    public Map addTeacher(@RequestBody User teacher){
        return Map.of("addteacher",userService.addUser(teacher));
    }

    //管理员删除教师
    @GetMapping("/manager/delteacher/{tid}")
    public Map delTeacher(@PathVariable int tid){
        return Map.of("delteacher",userService.deleteUser(tid));
    }

    //管理员添加监考信息
    @PostMapping("/manager/addexam")
    public Map addAExam(@RequestBody Exam exam,HttpServletResponse response){
        try {
            response.sendRedirect("/api/exams");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Map.of("addexam",examService.addExam(exam));
    }

    //管理员删除监考信息
    @DeleteMapping("/manager/delexam/{id}")
    public Map deleteExam(@PathVariable int id){
        examService.deleteExam(id);
        return Map.of("delexam",id);
    }

    //推荐监考教师
    @GetMapping("/manager/recommendteacher")
    public Map ascExecute(){
        List<User> list = userService.ascExecuteUser();
        return Map.of("ascExecuteUser",list);
    }

    //教师列表
    @GetMapping("/manager/teacherList")
    public Map teacherList(){
        List list  = userService.findByRole(User.roles.TEACHER);
        return Map.of("list",list,"pagination",Map.of("current",1,"pageSize",10,"total",list.size()));
    }
}
