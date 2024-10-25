package ru.mirea.manager.consumer;

import ru.mirea.model.TaskMessage;

public interface TaskConsumer {
    void consume(TaskMessage taskMessage);
}
