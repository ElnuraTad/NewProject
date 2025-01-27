package com.elnura.todolist.aiylbank.db.repository;

import com.elnura.todolist.aiylbank.db.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}
