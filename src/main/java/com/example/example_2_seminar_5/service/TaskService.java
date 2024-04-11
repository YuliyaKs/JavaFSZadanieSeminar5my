package com.example.example_2_seminar_5.service;

import com.example.example_2_seminar_5.model.Task;
import com.example.example_2_seminar_5.model.TaskStatus;
import com.example.example_2_seminar_5.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TaskService {

    private TaskRepository repository;

    // Вывести список всех задач
    public List<Task> getAllTasks() {
        return repository.findAll();
    }

    // Вывести задачу по ID
    public Optional<Task> getTaskById(Long id) {
        return repository.findById(id);
    }

    // Создать задачу
    public Task createTask(Task task) {
        return repository.save(task);
    }

    // Обновить задачу
    public Task updateTask(Long id, Task taskDetails) {
        Optional<Task> optionalTask = repository.findById(id);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            task.setDescription(taskDetails.getDescription());
            task.setStatus(taskDetails.getStatus());
            task.setCreationDate(LocalDateTime.now());
            return repository.save(task);
        } else {
            throw new IllegalArgumentException("Task not found with id: " + id);
        }
    }

    // Удалить задачу
    public void deleteTask(Long id) {
        repository.deleteById(id);
    }

    // Вывести задачи по статусу
    public List<Task> getTasksByStatus(TaskStatus status) {
        return repository.findTasksByStatus(status);

    }

    // Изменить статус задачи
    public Task updateTaskStatus(Long id) {
        Optional<Task> optionalTask = repository.findById(id);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            if (task.getStatus().equals(TaskStatus.NOT_STARTED)) {
                task.setStatus(TaskStatus.IN_PROGRESS);
            } else if (task.getStatus().equals(TaskStatus.IN_PROGRESS)) {
                task.setStatus(TaskStatus.COMPLETED);
            }
            return repository.save(task);
        } else {
            throw new IllegalArgumentException("Task not found with id: " + id);
        }
    }

}