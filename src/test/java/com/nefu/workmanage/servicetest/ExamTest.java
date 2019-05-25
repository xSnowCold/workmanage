package com.nefu.workmanage.servicetest;

import com.nefu.workmanage.entity.Exam;
import com.nefu.workmanage.service.ExamService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ExamTest {
    @Autowired
    ExamService examService;
    @Test
    public void add(){
        examService.addExam(new Exam("系统程序设计"));
    }
    @Test
    public void list(){
        List<Exam> list = examService.findAllExam();
        for(Exam exam:list){
            log.debug("{}",exam);
        }
    }
    @Test
    public void stat(){
        List<Exam> list  = examService.findExamStatus(Exam.stat.FINISH);
        for (Exam exam:list)
            log.debug("{}",exam);
    }
}
