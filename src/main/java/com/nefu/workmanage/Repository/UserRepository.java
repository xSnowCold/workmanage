package com.nefu.workmanage.Repository;

import com.nefu.workmanage.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    @Query("SELECT u FROM User u WHERE u.account=:account")
    User findByAccount(@Param("account") String account);
    @Query("SELECT u FROM User u order by u.executeTimes asc")
    List<User> ascExecute();
    @Query("SELECT u FROM User u ")
    List<User> findAll();
    @Query("SELECT u FROM User u WHERE u.role=:role")
    List<User> roleList(@Param("role") User.roles role);

    @Transactional
    @Modifying
    @Query(value = "update User u set u.executeTimes = u.executeTimes + 1 where u.id = :id")
    void changUserExecuteTimes(@Param("id") int id);
}
