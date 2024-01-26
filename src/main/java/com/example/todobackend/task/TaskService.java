package com.example.todobackend.task;

import com.example.todobackend.user.UserRepository;
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
    public TaskDTO delete(Long id) throws AccessDeniedException {
        Authentication authorizedUser = getAuthenticatedUserOrThrow();
        var taskEntity = taskRepository.findById(id).orElseThrow();
        taskRepository.delete(taskEntity);
        return new TaskDTO(taskEntity);

    }

    public TaskDTO add(TaskRequestBody reqBody) throws AccessDeniedException {
        Authentication authorizedUser = getAuthenticatedUserOrThrow();
        var userEntity = userRepository.findByUsernameIgnoreCase(reqBody.name())
                .orElseThrow(() -> new AccessDeniedException(""));
        var taskEntity = new Task();
        taskEntity.setName(reqBody.name());
        taskEntity.setCompleted(false);
        taskEntity.setDeadline(reqBody.deadline());
        taskEntity.setUser(userEntity); //temporary until auth has been implemented.
        taskEntity.setCompletedAt(null);
        taskRepository.save(taskEntity);
        return new TaskDTO(taskEntity);
    }

    public TaskDTO update(Long id, TaskRequestBody reqBody) throws AccessDeniedException {
        Authentication authorizedUser = getAuthenticatedUserOrThrow();
        var userEntity = userRepository.findByUsernameIgnoreCase(
                reqBody.name())
                .orElseThrow(() -> new AccessDeniedException(""));
        var taskEntity = taskRepository.findById(id).orElseThrow();
        taskEntity.setName(reqBody.name());
        taskEntity.setCompleted(false);
        taskEntity.setDeadline(reqBody.deadline());
        taskEntity.setUser(userEntity); //temporary until auth has been implemented.
        taskEntity.setCompletedAt(null);
        taskRepository.save(taskEntity);
        return new TaskDTO(taskEntity);

    }
}
