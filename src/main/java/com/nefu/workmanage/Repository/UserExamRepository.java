package com.nefu.workmanage.Repository;

import com.nefu.workmanage.entity.UserExam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserExamRepository extends JpaRepository<UserExam,Integer> {
}
