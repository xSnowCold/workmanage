package com.nefu.workmanage.Repository;

import com.nefu.workmanage.entity.Exam;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamRepository extends CustomizedRepository<Exam,Integer> {
}
