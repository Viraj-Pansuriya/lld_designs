package org.example.taskmanagementdesignap7.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Setter
@NoArgsConstructor
public class Task {

    private Long id;
    private String taskName;
    private String description;
    private TaskType type;
    private TaskStatus status;
    private TaskPriority priority;
    private User assignee;
    private User creator;
    private LocalDateTime dueDate;


}
