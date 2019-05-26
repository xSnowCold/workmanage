package com.nefu.workmanage.Repository;

import com.nefu.workmanage.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CustomizedRepository<User,Integer> {
}
