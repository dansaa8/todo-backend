package com.example.todobackend.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record TaskCreateBody(
        @NotNull @NotEmpty String name,
        @NotNull LocalDateTime deadline
) {
}
