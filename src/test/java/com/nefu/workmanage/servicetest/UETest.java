package com.nefu.workmanage.servicetest;

import com.nefu.workmanage.entity.UserExam;
import com.nefu.workmanage.service.ExamService;
import com.nefu.workmanage.service.UserExamService;
import com.nefu.workmanage.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class UETest {
    @Autowired
    UserExamService userExamService;
    @Autowired
    UserService userService;
    @Autowired
    ExamService examService;
    @Test
    public void add(){
        UserExam userExam = new UserExam();
        userExam.setUser(userService.findUser(2));
        userExam.setExam(examService.findAExam(1));
        userExamService.addUserExam(userExam);
    }
}
