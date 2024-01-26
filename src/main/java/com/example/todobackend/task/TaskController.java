package com.example.todobackend.task;

import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("api/tasks")
@Validated
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("")
    public List<TaskDTO> getAllTasks() throws AccessDeniedException {
        return taskService.getAll();
    }

    @PostMapping("")
    public TaskDTO addTask(@RequestBody @Valid TaskRequestBody requestBody) throws AccessDeniedException {
        return taskService.add(requestBody);
    }

    @DeleteMapping("/{id}")
    public TaskDTO deleteTask(@PathVariable Long id) throws AccessDeniedException {
        return taskService.delete(id);
    }

    @PutMapping("{id}")
    public TaskDTO updateTask(
            @PathVariable Long id,
            @RequestBody @Valid TaskRequestBody requestBody) throws AccessDeniedException {
        return taskService.update(id, requestBody);
    }
}
