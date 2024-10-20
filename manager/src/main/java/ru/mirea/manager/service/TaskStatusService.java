package ru.mirea.manager.service;

import ru.mirea.manager.domain.enums.TaskStatus;

public interface TaskStatusService {
    TaskStatus nextStatus(TaskStatus status);
}
