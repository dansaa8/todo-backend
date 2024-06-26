package com.example.todobackend.service.utils;

import com.example.todobackend.domain.Task;
import com.example.todobackend.domain.User;
import com.example.todobackend.repository.TaskRepository;
import com.example.todobackend.request.PatchBody;
import com.example.todobackend.request.CreateBody;
import com.example.todobackend.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.nio.file.AccessDeniedException;

@Component
public class TaskServiceUtils {

    private static UserRepository userRepository;
    private static TaskRepository taskRepository;

    private TaskServiceUtils(UserRepository userRepository, TaskRepository taskRepository) {
        TaskServiceUtils.userRepository = userRepository;
        TaskServiceUtils.taskRepository = taskRepository;
    }

    public static Authentication getAuthenticatedUserOrThrow() throws AccessDeniedException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new AccessDeniedException("");
        }
        return authentication;
    }

    public static User getUserByUsername(String username) throws AccessDeniedException {
        return userRepository.findByUsernameIgnoreCase(username)
                .orElseThrow(() -> new AccessDeniedException(""));
    }

    public static Task getTaskById(Long taskId) throws EntityNotFoundException {
        return taskRepository.findById(taskId)
                .orElseThrow(() -> new EntityNotFoundException(""));
    }

    public static void setValuesForNewTask(Task task, User user, CreateBody reqBody) {
        task.setName(reqBody.name());
        task.setDeadline(reqBody.deadline());
        task.setUser(user);
        task.setCompletedAt(null);
    }

    public static void patchExistingTask(Task task, PatchBody patchBody) {
        if (patchBody.name() != null) task.setName(patchBody.name());
        if (patchBody.description() != null) task.setDescription(patchBody.description());
        if (patchBody.deadline() != null) task.setDeadline(patchBody.deadline());
        if (patchBody.completedAt() != null) task.setCompletedAt(patchBody.completedAt());
    }

    public static boolean userOwnsTask(User userEntity, Task taskEntity) {
        return taskEntity.getUser().getId().equals(userEntity.getId());
    }
}
