package ru.mirea.worker.consumer;

import ru.mirea.domain.entity.Task;

public interface TaskConsumer {
    void consume(Task task);
}
