package com.nefu.workmanage.service;

import com.nefu.workmanage.Repository.UserRepository;
import com.nefu.workmanage.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {
    @Autowired
    UserRepository userRepository;

    //添加用户
    public User addUser(User user){
        userRepository.save(user);
        return userRepository.refresh(user);
    }
    //查找用户
    public User findUser(int id){
        return userRepository.findById(id).get();
    }
    public List<User> findUser(){
        return userRepository.findAll();
    }
    //删除用户
    public void deleteUser(int id){
        userRepository.deleteById(id);
    }
    public void deleteUser(User user){
        userRepository.delete(user);
    }
    //修改用户
    public User modifyUser(User user){
        userRepository.deleteById(user.getId());
        userRepository.flush();
        return userRepository.save(user);
    }
}
