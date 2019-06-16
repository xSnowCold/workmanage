package com.nefu.workmanage.controller;

import com.nefu.workmanage.entity.Task;
import com.nefu.workmanage.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;


@Slf4j
@RestController
@RequestMapping("/api")
public class TaskController {

    private TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }


    @GetMapping("/tasks")
    public Map getAllExam(){
        List<Task> taskList = taskService.getTaskList();
        return Map.of("list",taskList,"pagination",Map.of("current",1,"pageSize",10,"total",taskList.size()));
    }

    @GetMapping("/allTasks")
    public Map getAllTask() {
        List<Task> taskList = taskService.getTaskList();
        return Map.of("tasks", taskList);
    }

    @GetMapping("/tasks/{id}")
    public Map getTaskById(@PathVariable int id) {
        Task task = taskService.getTask(id);
        return Map.of("task", task);
    }

    @PostMapping("/manager/addTask")
    public Map addTask(@RequestBody Task task, HttpServletResponse response) {
        taskService.addTask(task);
        try {
            response.sendRedirect("/api/tasks");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Map.of("task", task);
    }

    @PostMapping("/manager/deleteTask")
    public Map deleteTask(@RequestBody Task task) {
        taskService.deleteTask(task);
        return Map.of("task", task);
    }


}
