package com.nefu.workmanage.service;

import com.nefu.workmanage.Repository.UserRepository;
import com.nefu.workmanage.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private EntityManager entityManager;
    //添加用户
    public User addUser(User user){
        String password = passwordEncoder.encode(user.getPassword());
        user.setPassword(password);
        return userRepository.save(user);
       // return userRepository.refresh(user);
    }

    //按ID查找用户
    public User findUser(int id){
        return userRepository.findById(id).get();
    }
    //查找全部用户
    public List<User> findUser(){
        return userRepository.findAll();
    }
    //按账号查找用户
    public User findByAccount(String account){
        return userRepository.findByAccount(account);
    }
    //按角色查找全部用户
    public List<User> findByRole(User.roles role){
        return userRepository.roleList(role);
    }
    //删除用户
    public boolean deleteUser(int id){
        userRepository.deleteById(id);
        return true;
    }
    public boolean deleteUser(User user){
        userRepository.delete(user);
        return true;
    }
    //修改用户
    public User modifyUser(User user){
        User user1 = userRepository.findById(user.getId()).get();
        user1 = user;
        String password = passwordEncoder.encode(user.getPassword());
        user1.setPassword(password);
        return userRepository.save(user1);
    }
    //按监考次数升序查询全部用户
    public List<User> ascExecuteUser(){
      return   userRepository.ascExecute();
    }

}
