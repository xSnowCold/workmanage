package com.nefu.workmanage.service;

import com.nefu.workmanage.Repository.ExamRepository;
import com.nefu.workmanage.entity.Exam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ExamService {
    @Autowired
    private ExamRepository examRepository;
    //添加考试
    public String addExam(Exam exam){
        if (isExam(exam)){
            examRepository.save(exam);
            //examRepository.refresh(exam);
            return "添加考试信息成功";
        }else {
            return "考试时间地点冲突请检查";
        }
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
        Exam exam1 = examRepository.findById(exam.getId()).get();
        exam1 = exam;
        return examRepository.save(exam1);
    }
    //按名称查询考试
    public List<Exam> queryExam(String subject){

        return examRepository.queryExamBySubject(subject);
    }

    //判断同一时间地点考试是否重合
    public boolean isExam(Exam isexam){
        List<Exam> list = examRepository.findAll();
        for(Exam exam:list){
            if(!(exam.getSite().equals(isexam.getSite()))){
                continue;
            }else if(isexam.getBeginTime().equals(exam.getBeginTime())||isexam.getEndTime().equals(exam.getEndTime())){
                return false;
            }else if (isexam.getBeginTime().isAfter(exam.getBeginTime())&&isexam.getBeginTime().isBefore(exam.getEndTime())){
                return false;
            }else if(isexam.getEndTime().isAfter(exam.getBeginTime())&&isexam.getEndTime().isBefore(exam.getEndTime())){
                return false;
            }else if(isexam.getBeginTime().isBefore(exam.getBeginTime())&&isexam.getEndTime().isAfter(exam.getEndTime())){
                return false;
            }

        }
        return true;

    }
}
