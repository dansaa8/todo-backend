package com.example.todobackend.task;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record TaskRequestBody(
        @NotNull String name,
        @NotNull LocalDateTime deadline
) {
}
