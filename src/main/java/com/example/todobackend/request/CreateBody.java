package com.example.todobackend.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record CreateBody(
        @NotNull @NotEmpty String name,
        String description,
        @NotNull LocalDateTime deadline
) {
}
