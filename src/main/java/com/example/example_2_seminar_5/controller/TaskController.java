package com.example.example_2_seminar_5.controller;

import com.example.example_2_seminar_5.model.Task;
import com.example.example_2_seminar_5.model.TaskStatus;
import com.example.example_2_seminar_5.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
@AllArgsConstructor
public class TaskController {

    private TaskService taskService;

    // Вывести список всех задач
    //localhost:8080/tasks
    @GetMapping()
    public List<Task> getAllTask(){
        return taskService.getAllTasks();
    }

    // Вывести задачу по ID
    //localhost:8080/tasks/1
    @GetMapping("/{id}")
    public Optional<Task> getTaskById(@PathVariable Long id){
        return taskService.getTaskById(id);
    }

    // Создать задачу
    //localhost:8080/tasks
    // RequestBody { "description": "Сходить в баню" }
    @PostMapping()
    public Task createTask(@RequestBody Task task){
        return taskService.createTask(task);
    }

    // Обновить задачу
    //localhost:8080/tasks/2
    // RequestBody { "description": "Заказать подарки", "status": "IN_PROGRESS" }
    @PutMapping("/{id}")
    public Task updateTask(@PathVariable("id") Long id, @RequestBody Task task){
        return taskService.updateTask(id, task);
    }

    // Удалить задачу
    //localhost:8080/tasks/3
    @DeleteMapping("/{id}")
    public String deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
        return String.format("Task No.%s has been deleted", id);
    }

    // Вывести задачи по статусу
    //localhost:8080/tasks/status/NOT_STARTED
    @GetMapping("/status/{status}")
    public List<Task> getTasksByStatus(@PathVariable TaskStatus status){
        return taskService.getTasksByStatus(status);
    }

    // Изменить статус задачи
    //localhost:8080/tasks/task/4
    @PutMapping("/task/{id}")
    public Task updateTaskStatus(@PathVariable Long id) {
        return taskService.updateTaskStatus(id);
    }


}

