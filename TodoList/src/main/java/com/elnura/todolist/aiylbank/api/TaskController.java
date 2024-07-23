package com.elnura.todolist.aiylbank.api;

import com.elnura.todolist.aiylbank.db.entity.Task;
import com.elnura.todolist.aiylbank.db.service.TaskService;
import com.elnura.todolist.aiylbank.exception.CustomErrorResponse;
import com.elnura.todolist.aiylbank.exception.InvalidDataException;
import com.elnura.todolist.aiylbank.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

//    Get all tasks
    @GetMapping
    public ResponseEntity<?> getAllTasks() {
        return ResponseEntity.ok(taskService.getAllTasks());
    }

//    Get task by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getTaskById(@PathVariable Long id) {
        try {
            Task task = taskService.getTaskById(id);
            return ResponseEntity.ok(task);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new CustomErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage()));
        }
    }

//    Create a new task
    @PostMapping
    public ResponseEntity<?> createTask(@RequestBody Task task) {
        try {
            Task createdTask = taskService.createTask(task);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
        } catch (InvalidDataException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new CustomErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage()));
        }
    }

//    Update task by ID
    @PutMapping("/{id}")
    public ResponseEntity<?> updateTask(@PathVariable Long id, @RequestBody Task task) {
        try {
            Task updatedTask = taskService.updateTask(id, task);
            return ResponseEntity.ok(updatedTask);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new CustomErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage()));
        } catch (InvalidDataException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new CustomErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage()));
        }
    }

//    Delete task by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id) {
        try {
            taskService.deleteTask(id);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new CustomErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage()));
        }
    }
}
