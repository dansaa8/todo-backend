package com.example.todobackend.task;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Objects;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<TaskView> getUserTasks(String userId) throws AccessDeniedException {
        Authentication user = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(user.getName().equals(userId));
        if (!user.isAuthenticated() || !user.getName().equals(userId)) {
            throw new AccessDeniedException("Unauthorized access");
        }
        return taskRepository.findMyTasks(userId);
    }
}
