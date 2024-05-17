package com.example.todobackend.service;

import com.example.todobackend.domain.Task;
import com.example.todobackend.dto.TaskDTO;
import com.example.todobackend.repository.TaskRepository;
import com.example.todobackend.request.TaskCreateBody;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.AccessDeniedException;
import java.util.List;

import static com.example.todobackend.service.utils.TaskServiceUtils.*;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<TaskDTO> getAll() throws AccessDeniedException {
        Authentication authorizedUser = getAuthenticatedUserOrThrow();
        return taskRepository.findTasksByUserUsername(authorizedUser.getName()).stream()
                .map(TaskDTO::new)
                .toList();
    }

    @Transactional
    public TaskDTO delete(Long id) throws AccessDeniedException, EntityNotFoundException {
        Authentication authorizedUser = getAuthenticatedUserOrThrow();
        var user = getUserByUsername(authorizedUser.getName());
        var task = getTaskById(id);
        if (userOwnsTask(user, task)) {
            taskRepository.delete(task);
            return new TaskDTO(task);
        }
        throw new AccessDeniedException("");
    }

    public TaskDTO add(TaskCreateBody reqBody) throws AccessDeniedException {
        Authentication authorizedUser = getAuthenticatedUserOrThrow();
        var user = getUserByUsername(authorizedUser.getName());
        var task = new Task();
        setTaskValues(task, user, reqBody);
        taskRepository.save(task);
        return new TaskDTO(task);
    }

    public TaskDTO update(Long id, TaskCreateBody reqBody) throws AccessDeniedException, EntityNotFoundException {
        Authentication authorizedUser = getAuthenticatedUserOrThrow();
        var user = getUserByUsername(authorizedUser.getName());
        var task = getTaskById(id);
        if (userOwnsTask(user, task)) {
            setTaskValues(task, user, reqBody);
            taskRepository.save(task);
            return new TaskDTO(task);
        }
        throw new AccessDeniedException("");
    }
}
