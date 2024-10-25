package ru.mirea.worker.processor;

import ru.mirea.model.TaskMessage;

public interface TaskProcessor {
    void process(TaskMessage taskMessage);
}
