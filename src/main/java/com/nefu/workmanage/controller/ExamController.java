package com.nefu.workmanage.controller;

import com.nefu.workmanage.entity.Exam;
import com.nefu.workmanage.service.ExamService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/api")
public class ExamController {
    @Autowired
    private ExamService examService;

    //获取全部监考信息
    @GetMapping("/exams")
    public Map getAllExam(){
        List<Exam> list = examService.findAllExam();
        return Map.of("list",list,"pagination",Map.of("current",1,"pageSize",10,"total",list.size()));
    }

    //根据ID获取监考详情
    @GetMapping("/exam/{id}")
    public Exam getExamById(@PathVariable int id){
        return examService.findAExam(id);
    }

    //根据状态获取监考信息
    @GetMapping("/exams/{status}")
    public Map getExamsByStatus(@PathVariable int status){
        return Map.of(""+status+"",examService.findExamStatus(status));
    }



}
