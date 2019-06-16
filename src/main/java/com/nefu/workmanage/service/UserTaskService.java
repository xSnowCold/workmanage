package com.nefu.workmanage.service;

import com.nefu.workmanage.Repository.TaskRepository;
import com.nefu.workmanage.Repository.UserTaskRepository;
import com.nefu.workmanage.entity.Task;
import com.nefu.workmanage.entity.User;
import com.nefu.workmanage.entity.UserTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@Service
@Transactional
public class UserTaskService {

    @Autowired
    private UserTaskRepository userTaskRepository;
    @Autowired
    private TaskRepository taskRepository;

    //    新建/修改用户任务
    public Map addUserTask(int taskId, List<User> userList) {
        UserTask userTask = new UserTask();
        for (User user : userList) {
            userTask = new UserTask();
            userTask.setUser(user);
            userTask.setTask(taskRepository.find(taskId));
            userTaskRepository.save(userTask);
        }
        return Map.of("userTasks", userTask);
    }

    //    显示所有用户任务
    public List<UserTask> userTaskList() {
        return userTaskRepository.findAll();
    }

    //    删除指定用户任务
    public void deleteUserTask(UserTask userTask) {
        userTaskRepository.delete(userTask);
    }

    //    用户回复任务消息
    public void reply(UserTask userTask) {
        int id = userTask.getId();
        Task task = userTaskRepository.find(id).getTask();
        if (task.getEndTime().isBefore(LocalDateTime.now())) {
            userTask.setStatus(2);
        } else {
            userTask.setStatus(1);
        }
        userTaskRepository.reply(userTask.getReply(), userTask.getStatus(),LocalDateTime.now(),userTask.getId());
    }
    //查询指定用户的所有任务
    public ArrayList utList(int uid) {
        List<List> userTasks = userTaskRepository.findByUser(uid);
        ArrayList arrayList = new ArrayList();
        for(int i=0;i<userTasks.size();i++) {
            Map map = new HashMap();
            map.put("id", userTasks.get(i).get(0));
            map.put("name", userTasks.get(i).get(1));
            map.put("detail", userTasks.get(i).get(2));
            map.put("status", userTasks.get(i).get(3));
            map.put("endTime", userTasks.get(i).get(4));
            arrayList.add(map);
        }
        return arrayList;
    }
    //    用户查看回复
    public String replay(int id){
        String replay=userTaskRepository.replay(id);
        return replay;
    }
    //    查询指定任务的所有用户
    public List<UserTask> tuList(int tid) {
        List<UserTask> userTasks = userTaskRepository.findByTask(tid);
        return userTasks;
    }

}
