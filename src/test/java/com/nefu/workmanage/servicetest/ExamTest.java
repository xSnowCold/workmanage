package com.nefu.workmanage.servicetest;

import com.nefu.workmanage.entity.Exam;
import com.nefu.workmanage.service.ExamService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Date;
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
        Exam exam = new Exam();
        exam.setBeginTime(LocalDateTime.parse("2019-06-05T13:40:00"));
        exam.setEndTime(LocalDateTime.parse("2019-05-27T15:40:00"));
        exam.setSite("丹青902");
        exam.setSubject("安卓开发");
        log.debug("{}", examService.addExam(exam));

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
