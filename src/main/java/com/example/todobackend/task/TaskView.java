package com.example.todobackend.task;

import java.time.LocalDateTime;

public interface TaskView {
    String getName();
    boolean getIsCompleted();
    LocalDateTime getDeadline();
    LocalDateTime getCompletedAt();
}
