package ru.mirea.worker.processor.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import ru.mirea.model.TaskMessage;
import ru.mirea.model.enums.TaskStatus;
import ru.mirea.worker.processor.TaskProcessor;
import ru.mirea.worker.producer.TaskProducer;
import ru.mirea.worker.properties.TaskProperties;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;

@Service
@Slf4j
@RequiredArgsConstructor
public class TaskProcessorImpl implements TaskProcessor {

    private final ThreadPoolTaskExecutor taskExecutor;
    private final TaskProducer taskProducer;
    private final TaskProperties taskProperties;

    /**
     * Имитация выполнения задачи в отдельном потоке со случайной задержкой.
     * Решение об успешном (ошибочном) завершении задач принимается в зависимости
     * от параметров, хранящихся в классе {@link TaskProperties}, который
     * содержит настройки задержки и вероятность успешного завершения.
     *
     * @param taskMessage Новая задача
     */
    @Override
    public void process(final TaskMessage taskMessage) {
        CompletableFuture.supplyAsync(() -> {
            startProcessing(taskMessage);
            simulateProcessing(taskMessage);
            return isCompleted() ? completeTask(taskMessage) : failTask(taskMessage);
        }, taskExecutor).thenAccept(taskProducer::sendMessage);
    }

    private void startProcessing(TaskMessage task) {
        task.setStatus(TaskStatus.PROCESSING);
        taskProducer.sendMessage(task);
    }

    private void simulateProcessing(TaskMessage task) {
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

    private TaskMessage failTask(TaskMessage task) {
        task.setStatus(TaskStatus.FAILED);
        log.info("Обработка задачи завершена [FAILED]: {}", task);
        return task;
    }

    private TaskMessage completeTask(TaskMessage task) {
        task.setStatus(TaskStatus.COMPLETED);
        task.setCompletedAt(LocalDateTime.now());
        log.info("Обработка задачи завершена [COMPLETED]: {}", task);
        return task;
    }

}
