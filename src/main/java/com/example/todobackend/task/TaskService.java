package com.example.todobackend.task;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<TaskView> getAllTasks() {
        return taskRepository.findAllBy();
    }

    @Transactional
    public TaskDto delete(Long id) {
        var taskEntity = taskRepository.findById(id).orElseThrow();
        taskRepository.delete(taskEntity);
        return new TaskDto(taskEntity);

    }

    public TaskDto add(TaskRequestBody reqBody) {
        var taskEntity = new Task();
        taskEntity.setName(reqBody.name());
        taskEntity.setCompleted(false);
        taskEntity.setDeadline(reqBody.deadline());
        taskEntity.setUserId(1L); //temporary until auth has been implemented.
        taskEntity.setCompletedAt(null);
        taskRepository.save(taskEntity);
        return new TaskDto(taskEntity);
    }

    public TaskDto update(Long id, TaskRequestBody reqBody) {
        var taskEntity = taskRepository.findById(id).orElseThrow();
        taskEntity.setName(reqBody.name());
        taskEntity.setCompleted(false);
        taskEntity.setDeadline(reqBody.deadline());
        taskEntity.setUserId(1L); //temporary until auth has been implemented.
        taskEntity.setCompletedAt(null);
        taskRepository.save(taskEntity);
        return new TaskDto(taskEntity);

    }

//    public List<TaskView> getUserTasks(String userId) throws AccessDeniedException {
//        Authentication user = SecurityContextHolder.getContext().getAuthentication();
//        System.out.println(user.getName().equals(userId));
//        if (!user.isAuthenticated() || !user.getName().equals(userId)) {
//            throw new AccessDeniedException("Unauthorized access");
//        }
//        return taskRepository.findMyTasks(userId);
//    }
}
