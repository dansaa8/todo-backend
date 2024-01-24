package com.example.todobackend.task;

import jakarta.validation.Valid;
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

    @PostMapping("/tasks")
    public TaskDto addTask(@RequestBody @Valid TaskRequestBody requestBody) {
        return taskService.add(requestBody);
    }

    @DeleteMapping("/tasks/{id}")
    public TaskDto deleteTask(@PathVariable Long id) {
        return taskService.delete(id);
    }

    @PutMapping("/tasks/{id}")
    public TaskDto updateTask(
            @PathVariable Long id,
            @RequestBody @Valid TaskRequestBody requestBody) {
        return taskService.update(id, requestBody); }

//    @GetMapping("/users/{userId}/tasks")
//    public List<TaskView> getUserTasks(@PathVariable String userId) throws AccessDeniedException {
//        return taskService.getUserTasks(userId);
//    }
}
