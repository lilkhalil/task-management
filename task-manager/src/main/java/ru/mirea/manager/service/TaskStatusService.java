package ru.mirea.manager.service;

import ru.mirea.domain.entity.enums.TaskStatus;

public interface TaskStatusService {
    TaskStatus nextStatus(TaskStatus status);
}
