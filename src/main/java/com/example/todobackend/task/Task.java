package com.example.todobackend.task;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "task", schema = "todo_app")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 20)
    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "user_id")
    private String userId;

    @NotNull
    @Column(name = "isCompleted", nullable = false)
    private boolean isCompleted = false;

    @NotNull
    @Column(name = "deadline", nullable = false)
    private LocalDateTime deadline;

    @Column(name = "completed_at", nullable = false)
    private LocalDateTime completedAt;
}
