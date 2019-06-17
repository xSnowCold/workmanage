package com.nefu.workmanage.controller;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nefu.workmanage.entity.Exam;
import com.nefu.workmanage.entity.UserExam;
import com.nefu.workmanage.service.ExamService;
import com.nefu.workmanage.service.UserExamService;
import com.nefu.workmanage.util.UtilExam;
import lombok.extern.slf4j.Slf4j;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/api")
public class ExamController {
    @Autowired
    private ExamService examService;
    @Autowired
    private UserExamService userExamService;
    @Autowired
    private ObjectMapper objectMapper;
    //获取全部监考信息
    @GetMapping("/exams")
    public Map getAllExam() throws IOException, JSONException {

        List<Exam> list = examService.findAllExam();
        List<UserExam> userExamList = null;
        List jsonList = new ArrayList();
        for(Exam exam:list){
            userExamList = userExamService.findUEById(exam.getId());
           String json = objectMapper.writeValueAsString(exam);
            UtilExam utilExam = objectMapper.readValue(json, UtilExam.class);
            StringBuffer sb = new StringBuffer();
            if(userExamList.size()!=0) {

                for (UserExam userExam : userExamList) {
                    sb.append(userExam.getUser().getName()+"|");
                }
                utilExam.setTeacher(sb);
            }else {
                sb.append("——");
                System.out.println("____+++++_____");
                utilExam.setTeacher(sb);
            }


            jsonList.add(utilExam);

        }
        return Map.of("list",jsonList,"pagination",Map.of("current",1,"pageSize",10,"total",list.size()));
    }

    //根据ID获取监考详情
    @GetMapping("/exam/{id}")
    public Exam getExamById(@PathVariable int id){
        return examService.findAExam(id);
    }

    //查询监考信息
    @GetMapping("/exams/query/{subject}")
    public Map queryExams(@PathVariable String subject){

        return Map.of("result"," ");
    }



}
