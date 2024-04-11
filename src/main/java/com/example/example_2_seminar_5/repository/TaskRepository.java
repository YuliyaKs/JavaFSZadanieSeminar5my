package com.example.example_2_seminar_5.repository;

import com.example.example_2_seminar_5.model.Task;
import com.example.example_2_seminar_5.model.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    // Найти задачу по статусу
    List<Task> findTasksByStatus(TaskStatus status);
}


