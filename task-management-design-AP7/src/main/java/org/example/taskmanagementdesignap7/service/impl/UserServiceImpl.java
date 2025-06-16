package org.example.taskmanagementdesignap7.service.impl;

import org.example.taskmanagementdesignap7.models.Task;
import org.example.taskmanagementdesignap7.models.TaskStatus;
import org.example.taskmanagementdesignap7.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class UserServiceImpl implements UserService {
    private final Map<Long , List<Task>> userWiseTasks = new ConcurrentHashMap<>();


    @Override
    public void addTask(long  userId, Task task){
        userWiseTasks.computeIfAbsent(userId , k-> new CopyOnWriteArrayList<>());
        userWiseTasks.get(userId).add(task);
    }

    @Override
    public void deleteTask(long  userId, Task task){
       userWiseTasks.get(userId).remove(task);

    }

    @Override
    public void markAsCompleted(long taskId, long userId){
        userWiseTasks.get(userId).stream().filter(task -> task.getId() == taskId).findFirst().ifPresent(task -> task.setStatus(TaskStatus.COMPLETED));
    }

    @Override
    public void updateTask(Long id, Task task) {

       if (userWiseTasks.containsKey(id) && userWiseTasks.get(id) != null) {
           List<Task> tasks = userWiseTasks.get(id);
           int index = tasks.indexOf(task);
           if (index != -1) {
               tasks.set(index, task);
           }
       }
    }
}
