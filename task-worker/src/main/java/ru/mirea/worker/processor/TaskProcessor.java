package ru.mirea.worker.processor;

import ru.mirea.domain.entity.Task;

public interface TaskProcessor {
    void process(Task task);
}
