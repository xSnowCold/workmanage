package com.nefu.workmanage.servicetest;

import com.nefu.workmanage.entity.User;
import com.nefu.workmanage.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class UserTest {
    @Autowired
    UserService userService;

    @Test
    public void addTest(){
        userService.addUser(new User("2016214404"));
    }
    @Test
    public void modify(){
        User user = userService.findUser(1);
        user.setTitle(User.titles.PROFESSOR);

        userService.modifyUser(user);
    }
}
