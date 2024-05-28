package com.example.todobackend.dto;

import com.example.todobackend.domain.Task;

import java.time.LocalDateTime;

public record TaskDTO(
        Long id,
        String name,
        String description,
        LocalDateTime deadline,
        LocalDateTime completedAt) {
    public TaskDTO(Task task) {
        this(
                task.getId(),
                task.getName(),
                task.getDescription(),
                task.getDeadline(),
                task.getCompletedAt()
        );
    }
}
