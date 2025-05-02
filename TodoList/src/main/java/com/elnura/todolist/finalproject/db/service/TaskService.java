package com.elnura.todolist.finalproject.db.service;

import com.elnura.todolist.finalproject.db.entity.Task;

import java.util.List;

public interface TaskService {
    List<Task> getAllTasks();
    Task getTaskById(Long id);
    Task createTask(Task task);
    Task updateTask(Long id, Task taskDetails);
    void deleteTask(Long id);
}
