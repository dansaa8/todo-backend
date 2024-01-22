package com.example.todobackend.task;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

//    @GetMapping("/users/{userId}/tasks")
//    public List<TaskView> getUserTasks(@PathVariable String userId) throws AccessDeniedException {
//        return taskService.getUserTasks(userId);
//    }
}
