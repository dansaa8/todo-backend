package com.example.todobackend.service;

import com.example.todobackend.domain.Task;
import com.example.todobackend.dto.TaskDTO;
import com.example.todobackend.repository.TaskRepository;
import com.example.todobackend.request.PatchBody;
import com.example.todobackend.request.CreateBody;
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

    public TaskDTO getById(Long id) throws AccessDeniedException {
        Authentication authorizedUser = getAuthenticatedUserOrThrow();
        var user = getUserByUsername(authorizedUser.getName());
        var task = getTaskById(id);
        if (userOwnsTask(user, task)) {
            return new TaskDTO(task);
        }
        throw new AccessDeniedException("");
    }

    @Transactional
    public TaskDTO add(CreateBody reqBody) throws AccessDeniedException {
        Authentication authorizedUser = getAuthenticatedUserOrThrow();
        var user = getUserByUsername(authorizedUser.getName());
        var task = new Task();
        setValuesForNewTask(task, user, reqBody);
        taskRepository.save(task);
        return new TaskDTO(task);
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

    @Transactional
    public TaskDTO patch(Long id, PatchBody patchBody) throws AccessDeniedException, EntityNotFoundException {
        Authentication authorizedUser = getAuthenticatedUserOrThrow();
        var user = getUserByUsername(authorizedUser.getName());
        var task = getTaskById(id);
        if (userOwnsTask(user, task)) {
            patchExistingTask(task, patchBody);
            taskRepository.save(task);
            return new TaskDTO(task);
        }
        throw new AccessDeniedException("");
    }

}
