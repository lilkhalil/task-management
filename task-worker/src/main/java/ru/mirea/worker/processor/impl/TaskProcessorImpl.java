package ru.mirea.worker.processor.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import ru.mirea.domain.entity.Task;
import ru.mirea.domain.entity.enums.TaskStatus;
import ru.mirea.worker.processor.TaskProcessor;
import ru.mirea.worker.properties.TaskProperties;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;

@Service
@Slf4j
@RequiredArgsConstructor
public class TaskProcessorImpl implements TaskProcessor {

    private final ThreadPoolTaskExecutor taskExecutor;
    private final TaskProperties taskProperties;

    /**
     * Имитация выполнения задача, случайная задержка 5-10 секунд
     *
     * @param task Новая задача
     */
    @Override
    public CompletableFuture<Task> process(final Task task) {
        return CompletableFuture.supplyAsync(() -> {
            simulateProcessing(task);
            return isCompleted()
                    ? completeTask(task)
                    : failTask(task);
        }, taskExecutor);
    }

    private void simulateProcessing(Task task) {
        log.info("Обработка задачи: {}", task);
        long origin = taskProperties.getLowerBound();
        long bound = taskProperties.getUpperBound();
        try {
            Thread.sleep(ThreadLocalRandom.current().nextLong(origin, bound));
        } catch (InterruptedException ex) {
            log.error("Обработка задачи прервана: ", ex);
        }
    }

    private boolean isCompleted() {
        return Math.random() < taskProperties.getCompletionProbability();
    }

    private Task failTask(Task task) {
        task.setStatus(TaskStatus.FAILED);
        log.info("Обработка задачи завершена [FAILED]: {}", task);
        return task;
    }

    private Task completeTask(Task task) {
        task.setStatus(TaskStatus.COMPLETED);
        task.setCompletedAt(LocalDateTime.now());
        log.info("Обработка задачи завершена [COMPLETED]: {}", task);
        return task;
    }

}
