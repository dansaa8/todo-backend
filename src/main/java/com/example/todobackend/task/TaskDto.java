package com.example.todobackend.task;

import java.time.LocalDateTime;

public record TaskDto(
        Long id,
        String name,
        boolean isCompleted,
        LocalDateTime deadline,
        LocalDateTime completedAt) {
    public TaskDto(Task task) {
        this(
                task.getId(),
                task.getName(),
                task.isCompleted(),
                task.getDeadline(),
                task.getCompletedAt()
        );
    }
}
