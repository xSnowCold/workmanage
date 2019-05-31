package com.nefu.workmanage.controller;

import com.nefu.workmanage.entity.Exam;
import com.nefu.workmanage.service.ExamService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
        return Map.of("exams",examService.findAllExam());
    }

    //根据状态获取监考信息
    @GetMapping("/exams/{status}")
    public Map getExamsByStatus(@PathVariable Exam.stat status){
        return Map.of(""+status+"",examService.findExamStatus(status));
    }

    //添加监考信息
    @PostMapping("/exams")
    public Map addAExam(@RequestBody Exam exam){
       return Map.of("反馈",examService.addExam(exam));
    }

    //删除教考信息
    @DeleteMapping("/exams/{id}")
    public Map deleteExam(@PathVariable int id){
        examService.deleteExam(id);
        return Map.of("删除",id);
    }

}
