package org.example.taskmanagementdesignap7.service;

import org.example.taskmanagementdesignap7.models.Task;

public interface UserService {
    void addTask(long  userId, Task task);

    void deleteTask(long  userId, Task task);

    void markAsCompleted(long taskId, long userId);
}
