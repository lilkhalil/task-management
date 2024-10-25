package ru.mirea.worker.producer;

import ru.mirea.model.TaskMessage;

public interface TaskProducer {
    void sendMessage(TaskMessage task);
}
