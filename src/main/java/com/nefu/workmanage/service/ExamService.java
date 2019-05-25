package com.nefu.workmanage.service;

import com.nefu.workmanage.Repository.ExamRepository;
import com.nefu.workmanage.entity.Exam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ExamService {
    @Autowired
    ExamRepository examRepository;
    //添加考试
    public Exam addExam(Exam exam){
        return examRepository.save(exam);
    }
    //删除考试
    public void deleteExam(int id){
        examRepository.deleteById(id);
    }
    //查找考试
    public Exam findAExam(int id){
        return examRepository.findById(id).get();
    }
    public List<Exam> findAllExam(){
        return examRepository.findAll();
    }
    //修改考试
    public Exam modifyExam(Exam exam){
        examRepository.findById(exam.getId());
        examRepository.flush();
        return examRepository.save(exam);
    }
    //获取考试状态分列表
    public List<Exam> findExamStatus(Exam.stat status){
        List<Exam> list = examRepository.findAll();
        List<Exam> un = new ArrayList<>();
        for(Exam exam:list){
            if(exam.getStatus().equals(status)){
              un.add(exam);
            }
        }
        return un;
    }

}
