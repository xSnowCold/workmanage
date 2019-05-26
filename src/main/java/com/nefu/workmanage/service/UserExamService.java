package com.nefu.workmanage.service;

import com.nefu.workmanage.Repository.UserExamRepository;
import com.nefu.workmanage.entity.UserExam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserExamService {
    @Autowired
    UserExamRepository userExamRepository;

    public UserExam addUserExam(UserExam userExam){
        userExamRepository.save(userExam);
        return userExamRepository.refresh(userExam);
    }
}
