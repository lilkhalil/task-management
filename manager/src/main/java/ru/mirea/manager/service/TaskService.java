package ru.mirea.manager.service;

import ru.mirea.manager.domain.Task;
import ru.mirea.manager.domain.enums.TaskStatus;
import ru.mirea.manager.dto.TaskRqDto;

import java.util.List;

public interface TaskService {
    List<Task> findAll(TaskStatus taskStatus);
    Task findById(Long taskId);
    Task createTask(TaskRqDto requestBody);
}
