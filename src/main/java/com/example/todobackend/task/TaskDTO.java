package com.example.todobackend.task;

import java.time.LocalDateTime;

public record TaskDTO(
        Long id,
        String name,
        boolean isCompleted,
        LocalDateTime deadline,
        LocalDateTime completedAt) {
    public TaskDTO(Task task) {
        this(
                task.getId(),
                task.getName(),
                task.isCompleted(),
                task.getDeadline(),
                task.getCompletedAt()
        );
    }
}
