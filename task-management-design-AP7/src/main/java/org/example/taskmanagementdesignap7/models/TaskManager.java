package org.example.taskmanagementdesignap7.models;

import org.example.taskmanagementdesignap7.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

@Component
public class TaskManager {

    private static TaskManager taskManager;
    private Map<Long , Task> tasks;
    private final UserService userService;

    public TaskManager(UserService userService) {
        this.userService = userService;
    }


    public void createTask(Task task){
        tasks.putIfAbsent(task.getId() , task);
        userService.addTask(task.getAssignee().getId() , task);
    }

    public void assignTask(Long taskId , User user){
        Task task = tasks.get(taskId);
        unassignTask(task);
        task.setAssignee(user);
        userService.addTask(user.getId(),tasks.get(taskId));
    }


    public void markAsCompleted(Long taskId , Long userId){
        Task task = tasks.get(taskId);
        task.setStatus(TaskStatus.COMPLETED);
        userService.markAsCompleted(taskId,userId);
    }

    public void updateTask(Task task){

        // TODO : implement

    }

    public void unassignTask(Task task){
        long userId = task.getAssignee().getId();

        if(tasks.get(task.getId()) == null || tasks.get(task.getId()).getAssignee() == null){
            return;
        }
        userService.deleteTask(userId , task);
        task.setAssignee(null);
    }

    public List<Task> searchTasks(String keywords){
        return tasks.values().stream()
                .filter(task -> task.getTaskName().contains(keywords) || task.getDescription().contains(keywords)).toList();

    }

    public List<Task> filterTasks(TaskStatus status , TaskPriority priority , LocalDateTime startDate , LocalDateTime endDate){

        return
                tasks.values()
                        .stream()
                        .filter(getFilterPredicate(status , priority , startDate , endDate))
                        .toList();

    }

    public Predicate<Task> getFilterPredicate(TaskStatus status , TaskPriority priority , LocalDateTime startDate , LocalDateTime endDate){
        Predicate<Task> predicate = task -> true;

        if (status != null) {
            predicate = predicate.and(task -> task.getStatus() == status);
        }
        if (priority != null) {
            predicate = predicate.and(task -> task.getPriority() == priority);
        }
        if (startDate != null) {
            predicate = predicate.and(task -> task.getDueDate().isAfter(startDate));
        }
        if (endDate != null) {
            predicate = predicate.and(task -> task.getDueDate().isBefore(endDate));
        }

        return predicate;
    }
}
