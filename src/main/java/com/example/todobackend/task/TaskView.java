package com.example.todobackend.task;

import java.time.LocalDateTime;

public interface TaskView {
    Long getId();
    String getName();
    boolean getIsCompleted();
    LocalDateTime getDeadline();
    LocalDateTime getCompletedAt();
}
