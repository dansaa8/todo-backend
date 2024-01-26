package com.example.todobackend.task;

import com.example.todobackend.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.annotations.NotFound;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.AccessDeniedException;
import java.util.List;

import static com.example.todobackend.task.TaskUtils.getAuthenticatedUserOrThrow;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskService(TaskRepository taskRepository,
                       UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public List<TaskDTO> getAll() throws AccessDeniedException {
        Authentication authorizedUser = getAuthenticatedUserOrThrow();
        return taskRepository.findTasksByUserUsername(authorizedUser.getName()).stream()
                .map(TaskDTO::new)
                .toList();
    }

    @Transactional
    public TaskDTO delete(Long idDelete) throws AccessDeniedException, EntityNotFoundException {
        Authentication authorizedUser = getAuthenticatedUserOrThrow();

        var fkUserEntity = userRepository.findByUsernameIgnoreCase(authorizedUser.getName()).orElseThrow();

        var taskEntity = taskRepository.findById(idDelete).orElseThrow(() ->
                new EntityNotFoundException("Task not found"));

        if (taskEntity.getUser().getId().equals(fkUserEntity.getId())) {
            taskRepository.delete(taskEntity);
            return new TaskDTO(taskEntity);
        }
        throw new AccessDeniedException("");
    }

    public TaskDTO add(TaskRequestBody reqBody) throws AccessDeniedException {
        Authentication authorizedUser = getAuthenticatedUserOrThrow();

        var fkUserEntity = userRepository.findByUsernameIgnoreCase(authorizedUser.getName())
                .orElseThrow(() -> new AccessDeniedException(""));

        var taskEntity = new Task();
        taskEntity.setName(reqBody.name());
        taskEntity.setCompleted(false);
        taskEntity.setDeadline(reqBody.deadline());
        taskEntity.setUser(fkUserEntity); //temporary until auth has been implemented.
        taskEntity.setCompletedAt(null);
        taskRepository.save(taskEntity);
        return new TaskDTO(taskEntity);
    }

    public TaskDTO update(Long idUpdate, TaskRequestBody reqBody) throws AccessDeniedException, EntityNotFoundException {
        Authentication authorizedUser = getAuthenticatedUserOrThrow();

        var fkUserEntity = userRepository.findByUsernameIgnoreCase(authorizedUser.getName()).orElseThrow();


        var taskEntity = taskRepository.findById(idUpdate).orElseThrow(() ->
                new EntityNotFoundException("Task not found"));

        if (taskEntity.getUser().getId().equals(fkUserEntity.getId())) {
            taskEntity.setName(reqBody.name());
            taskEntity.setCompleted(false);
            taskEntity.setDeadline(reqBody.deadline());
            taskEntity.setUser(fkUserEntity); //temporary until auth has been implemented.
            taskEntity.setCompletedAt(null);
            taskRepository.save(taskEntity);
            return new TaskDTO(taskEntity);
        }

        throw new AccessDeniedException("");
    }
}
