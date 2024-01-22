package com.example.todobackend.task;

import org.springframework.stereotype.Service;

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

//    public List<TaskView> getUserTasks(String userId) throws AccessDeniedException {
//        Authentication user = SecurityContextHolder.getContext().getAuthentication();
//        System.out.println(user.getName().equals(userId));
//        if (!user.isAuthenticated() || !user.getName().equals(userId)) {
//            throw new AccessDeniedException("Unauthorized access");
//        }
//        return taskRepository.findMyTasks(userId);
//    }
}
