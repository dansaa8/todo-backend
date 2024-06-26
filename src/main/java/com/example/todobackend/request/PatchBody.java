    package com.example.todobackend.request;

    import java.time.LocalDateTime;

    public record PatchBody(
            String name,
            String description,
            LocalDateTime deadline,
            LocalDateTime completedAt
    ) {
    }
