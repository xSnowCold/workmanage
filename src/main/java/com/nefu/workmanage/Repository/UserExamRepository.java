package com.nefu.workmanage.Repository;

import com.nefu.workmanage.entity.User;
import com.nefu.workmanage.entity.UserExam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserExamRepository extends JpaRepository<UserExam,Integer> {
    @Query("SELECT ue FROM UserExam ue WHERE ue.exam.id=:id")
    List<UserExam> findUserExamByExamId(@Param("id") int id);

    @Query("SELECT ue FROM UserExam ue WHERE ue.user.id=:id")
    List<UserExam> findUserExamByUserId(@Param("id") int id);


}
