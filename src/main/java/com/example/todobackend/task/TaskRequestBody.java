package com.example.todobackend.task;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record TaskRequestBody(
        @NotNull @NotEmpty String name,
        @NotNull LocalDateTime deadline
) {
}
