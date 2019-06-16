package com.nefu.workmanage.Repository;

import com.nefu.workmanage.entity.UserTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface UserTaskRepository extends JpaRepository<UserTask,Integer> {
    //    按照id查询任务
    @Query("select ut from UserTask ut where ut.id=:id")
    UserTask find(@Param("id") int id);

    @Transactional
    @Modifying
    @Query("update UserTask ut set ut.reply=:reply,ut.status=:status,ut.finishTime=:finishTime where ut.id=:id")
    void reply(@Param("reply") String reply, @Param("status") int status, @Param("finishTime") LocalDateTime finishTime, @Param("id") int id);

    @Query("select ut.id,ut.task.name,ut.task.detail,ut.status,ut.task.endTime from UserTask ut where ut.user.id=:uid")
    List<List> findByUser(@Param("uid") int uid);

    @Query("select ut from UserTask ut where ut.task.id=:tid")
    List<UserTask> findByTask(@Param("tid") int tid);

    @Query("select ut.reply from UserTask ut where ut.id=:id")
    String replay(@Param("id") int id);
}
