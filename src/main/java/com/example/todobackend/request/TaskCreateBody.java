package com.example.todobackend.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

import java.time.LocalDateTime;

public record TaskCreateBody(
        @NotNull @NotEmpty String name,
        String description,
        @NotNull LocalDateTime deadline
) {
}
