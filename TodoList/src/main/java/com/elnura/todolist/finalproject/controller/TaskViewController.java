package com.elnura.todolist.finalproject.controller;

//import ch.qos.logback.core.model.Model;
import com.elnura.todolist.finalproject.db.service.TaskService;
import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.elnura.todolist.finalproject.db.entity.Task;

@Controller
public class TaskViewController {

    private final TaskService taskService;

    public TaskViewController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/tasks/view")
    public String showTaskPage(Model model) {
        model.addAttribute("task", new Task());
        model.addAttribute("tasks", taskService.getAllTasks());
        return "tasks";
    }

    @PostMapping("/tasks/create")
    public String createTask(@ModelAttribute Task task) {
        taskService.createTask(task);
        return "redirect:/tasks/view";
    }

//    private final TaskService taskService;
//
//    public TaskViewController(TaskService taskService) {
//        this.taskService = taskService;
//    }
//
//    @GetMapping("/tasks/view")
//    public String showTasksPage(Model model) {
//        model.addAttribute("tasks", taskService.getAllTasks());
//        model.addAttribute("task", new Task()); // для формы
//        return "tasks"; // templates/tasks.html
//    }
//
//    @PostMapping("/tasks/create")
//    public String createTaskFromForm(@ModelAttribute Task task) {
//        taskService.createTask(task);
//        return "redirect:/tasks/view";
//    }
}
