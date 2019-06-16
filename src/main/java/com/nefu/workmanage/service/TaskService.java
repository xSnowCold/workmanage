package com.nefu.workmanage.service;

import com.nefu.workmanage.Repository.TaskRepository;
import com.nefu.workmanage.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    //    查找全部任务
    public List<Task> getTaskList() {
        return taskRepository.findAll();
    }
    //    按照ID查询任务
    public Task getTask(int id) {
        return taskRepository.find(id);
    }
    //    新增任务
    public void addTask(Task task) {
        taskRepository.save(task);
    }
    //    删除任务
    public void deleteTask(Task task) {
        taskRepository.delete(task);
    }

}

