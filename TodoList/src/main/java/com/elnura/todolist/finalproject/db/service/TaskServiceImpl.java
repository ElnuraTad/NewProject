package com.elnura.todolist.finalproject.db.service;

import com.elnura.todolist.finalproject.db.entity.Task;
import com.elnura.todolist.finalproject.db.repository.TaskRepository;
import com.elnura.todolist.finalproject.exception.InvalidDataException;
import com.elnura.todolist.finalproject.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final EmailService emailService;

    public TaskServiceImpl(TaskRepository taskRepository, EmailService emailService) {
        this.taskRepository = taskRepository;
        this.emailService = emailService;
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));
    }

    @Override
    public Task createTask(Task task) {
        if (task.getDescription() == null || task.getDescription().isEmpty()) {
            throw new InvalidDataException("Task description cannot be empty");
        }

        Task savedTask = taskRepository.save(task);
        emailService.sendTaskCreatedNotification(savedTask); // ✅ добавляем отправку письма
        return savedTask;
    }

    @Override
    public Task updateTask(Long id, Task taskDetails) {
        if (taskDetails.getDescription() == null || taskDetails.getDescription().isEmpty()) {
            throw new InvalidDataException("Task description cannot be empty");
        }
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));

        task.setDescription(taskDetails.getDescription());
        task.setCompleted(taskDetails.isCompleted());

        return taskRepository.save(task);
    }

    @Override
    public void deleteTask(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));

        taskRepository.delete(task);
    }
//    @Autowired
//    private TaskRepository taskRepository;
//
//    @Autowired
//    private final EmailService emailService;
//
//    public TaskServiceImpl(EmailService emailService) {
//        this.emailService = emailService;
//    }
//
//    @Override
//    public List<Task> getAllTasks() {
//        return taskRepository.findAll();
//    }
//
//    @Override
//    public Task getTaskById(Long id) {
//        return taskRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));
//    }
//
//    @Override
//    public Task createTask(Task task) {
//        if (task.getDescription() == null || task.getDescription().isEmpty()) {
//            throw new InvalidDataException("Task description cannot be empty");
//        }
//
//        return taskRepository.save(task);
//    }
//
//    @Override
//    public Task updateTask(Long id, Task taskDetails) {
//        if (taskDetails.getDescription() == null || taskDetails.getDescription().isEmpty()) {
//            throw new InvalidDataException("Task description cannot be empty");
//        }
//        Task task = taskRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));
//
//        task.setDescription(taskDetails.getDescription());
//        task.setCompleted(taskDetails.isCompleted());
//
//        return taskRepository.save(task);
//    }
//
//    @Override
//    public void deleteTask(Long id) {
//        Task task = taskRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));
//
//        taskRepository.delete(task);
//    }
}

