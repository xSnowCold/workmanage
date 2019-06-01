package com.nefu.workmanage.servicetest;

import com.nefu.workmanage.entity.User;
import com.nefu.workmanage.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class UserTest {
    @Autowired
    UserService userService;

    @Test
    public void addTest(){
        User user = new User();
        user.setAccount("2018224456");
        user.setPassword("101010");
        user.setName("sk");

        userService.addUser(user);
    }
    @Test
    public void modify(){
        User user = userService.findUser(1);
        user.setTitle(User.titles.PROFESSOR);

        userService.modifyUser(user);
    }
    @Test
    public void findAll(){
        List<User> list = userService.findUser();
        for(User user:list){
            log.debug("{}", user.getAccount());
        }


    }
}
