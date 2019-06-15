package com.nefu.workmanage.component;

import com.nefu.workmanage.entity.Exam;
import com.nefu.workmanage.entity.UserExam;
import com.nefu.workmanage.service.ExamService;
import com.nefu.workmanage.service.UserExamService;
import com.nefu.workmanage.service.UserService;
import com.nefu.workmanage.util.IsToday;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Mytimer {
    @Autowired
    private UserExamService userExamService;
    @Autowired
    private UserService userService;
    @Autowired
    private ExamService examService;
    private IsToday isToday;
    @Scheduled(cron = "0 0 * * * *")
    public void notice(){

        //isToday.isNow(userExamService.);

    }
}
