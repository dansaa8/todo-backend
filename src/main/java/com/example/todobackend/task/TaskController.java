package com.example.todobackend.task;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("api")
@Validated
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // Temporary
    @GetMapping("/tasks")
    public List<TaskView> getAllTasks() {
        return taskService.getAllTasks();
    }

    @DeleteMapping("/tasks/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.delete(id);
    }

//    @GetMapping("/users/{userId}/tasks")
//    public List<TaskView> getUserTasks(@PathVariable String userId) throws AccessDeniedException {
//        return taskService.getUserTasks(userId);
//    }
}
