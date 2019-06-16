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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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
    @Scheduled(cron = "0 14 0 * * *")
    public List notice(){
        String nowTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        System.out.println(nowTime);
        List<UserExam> noticeList = new ArrayList();
        List<UserExam> list = userExamService.findAllUserExam();
        for(UserExam userExam:list){
            String dbBeginTime = userExam.getExam().getBeginTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            System.out.println(dbBeginTime);
            if(nowTime.equals(dbBeginTime)){
                noticeList.add(userExam);
            }
        }
        for(UserExam userExam:noticeList){
            System.out.println(""+userExam.getExam().getSubject()+"");
        }
        return noticeList;
    }
}
