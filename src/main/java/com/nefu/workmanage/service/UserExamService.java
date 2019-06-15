package com.nefu.workmanage.service;

import com.nefu.workmanage.Repository.ExamRepository;
import com.nefu.workmanage.Repository.UserExamRepository;
import com.nefu.workmanage.Repository.UserRepository;
import com.nefu.workmanage.entity.Exam;
import com.nefu.workmanage.entity.User;
import com.nefu.workmanage.entity.UserExam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionUsageException;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@Slf4j
public class UserExamService {
    @Autowired
   private UserExamRepository userExamRepository;
    @Autowired
    private ExamRepository examRepository;
    @Autowired
    private UserRepository userRepository;
    //分配监考教师
    public Map addUserExam(Exam exam, List<User> teachers){
        Exam exam1 = examRepository.findById(exam.getId()).get();
        userExamRepository.flush();
        Map map = new HashMap();
        for (User teacher : teachers){
            //冲突提示
            if(isUserExam(exam1, teacher)){
                map.put(""+exam1.getSubject()+"-"+userRepository.findById(teacher.getId()).get().getName()+"","分配成功");
            }else {
                map.put(""+exam1.getSubject()+"-"+userRepository.findById(teacher.getId()).get().getName()+"","信息已保存，监考冲突");
            }
                UserExam userExam = new UserExam();
                userExam.setExam(exam1);
                userExam.setUser(teacher);
                userExamRepository.save(userExam);
                userExamRepository.flush();
                System.out.println(teacher.getId());


        }
        return map;
    }
    //根据用户ID获取监考信息
    public List<UserExam> findUEByUid(int id){
        return userExamRepository.findUserExamByExamId(id);
    }
    //修改监考分配
    //根据考试ID获取监考信息
    public List<UserExam> findUEById(int id){
        return  userExamRepository.findUserExamByExamId(id);
    }

    //判断是否监考冲突
    public boolean isUserExam(Exam exam,User user){
        LocalDateTime nowBeginTime = exam.getBeginTime();
        LocalDateTime nowEndTime = exam.getEndTime();
        System.out.println(""+nowBeginTime+":::::"+nowEndTime+"");
            List<UserExam> ueList = userExamRepository.findUserExamByUserId(user.getId());
        System.out.println(ueList.size());
            for (UserExam ue : ueList) {
                System.out.println(ue.getUser().getName());
                LocalDateTime dbBeginTime = ue.getExam().getBeginTime();
                LocalDateTime dbEndTime = ue.getExam().getEndTime();
                System.out.println(""+dbBeginTime+"-"+dbEndTime+"");
                if (nowBeginTime.isAfter(dbBeginTime) && nowBeginTime.isBefore(dbEndTime)) {
                    return false;
                }else if (nowEndTime.isAfter(dbBeginTime) && nowEndTime.isBefore(dbEndTime)) {
                    return false;
                }else if (nowBeginTime.isBefore(dbBeginTime) && nowEndTime.isAfter(dbEndTime)) {
                    return false;
                }else if (nowBeginTime.equals(dbBeginTime) && nowEndTime.equals(dbEndTime)) {
                    return false;
                }
            }
        return true;
    }
}
