package com.nefu.workmanage.Repository;

import com.nefu.workmanage.entity.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ExamRepository extends JpaRepository<Exam,Integer> {
    @Transactional
    @Modifying
    @Query(value = "update Exam e set e.status = 1 where e.id = :id")
    void changExamStatus(@Param("id") int id);

   /* @Query("SELECT e FROM Exam e WHERE e.status=:status")
    List<Exam> queryExamByStatus(@Param("status") String status);

    @Query("SELECT e FROM Exam e WHERE e.status=:status")
    List<Exam> queryExamBySubject(@Param("subject") String subject);

    @Query("SELECT e FROM Exam e WHERE e.beginTime=:beginTime")
    List<Exam> queryExamByBeginTime(@Param("beginTime") String beginTime);*/
}
