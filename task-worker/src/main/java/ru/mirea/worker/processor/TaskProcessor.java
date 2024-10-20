package ru.mirea.worker.processor;

import ru.mirea.domain.entity.Task;

import java.util.concurrent.CompletableFuture;

public interface TaskProcessor {
    CompletableFuture<Task> process(Task task);
}
