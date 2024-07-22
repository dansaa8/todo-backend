package com.example.todobackend.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PatchBody {
    private String name;
    private String description;
    private LocalDateTime deadline;
    private LocalDateTime completedAt;

    // Flags to track presence of fields
    private boolean namePresent;
    private boolean descriptionPresent;
    private boolean deadlinePresent;
    private boolean completedAtPresent;

    // Custom setters to update presence flags
    @JsonSetter
    public void setName(String name) {
        this.name = name;
        this.namePresent = true;
    }

    @JsonSetter
    public void setDescription(String description) {
        this.description = description;
        this.descriptionPresent = true;
    }

    @JsonSetter
    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
        this.deadlinePresent = true;
    }

    @JsonSetter
    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
        this.completedAtPresent = true;
    }
}
