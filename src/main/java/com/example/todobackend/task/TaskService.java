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
    public void delete(Long id) {
        var taskEntity = taskRepository.findById(id).orElseThrow();
        taskRepository.delete(taskEntity);
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
