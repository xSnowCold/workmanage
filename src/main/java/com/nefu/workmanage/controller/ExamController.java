package com.nefu.workmanage.controller;

import com.nefu.workmanage.service.ExamService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/api")
public class ExamController {
    @Autowired
    private ExamService examService;

    @GetMapping("/exams")
    public Map getAllExam(){
        return Map.of("exams",examService.findAllExam());
    }
}
