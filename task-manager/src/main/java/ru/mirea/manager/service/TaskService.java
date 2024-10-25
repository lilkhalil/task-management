package ru.mirea.manager.service;

import ru.mirea.manager.dto.TaskRqDto;
import ru.mirea.manager.entity.Task;
import ru.mirea.manager.entity.enums.TaskStatus;

import java.util.List;

public interface TaskService {
    List<Task> findAll(TaskStatus taskStatus);
    Task findById(Long taskId);
    Task createTask(TaskRqDto requestBody);
}
