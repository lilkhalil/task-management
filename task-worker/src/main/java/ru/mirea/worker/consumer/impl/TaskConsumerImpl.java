package ru.mirea.worker.consumer.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.mirea.domain.entity.Task;
import ru.mirea.domain.entity.enums.TaskStatus;
import ru.mirea.domain.repository.TaskRepository;
import ru.mirea.worker.processor.TaskProcessor;
import ru.mirea.worker.consumer.TaskConsumer;

@Service
@RequiredArgsConstructor
@Slf4j
public class TaskConsumerImpl implements TaskConsumer {

    private final TaskProcessor taskProcessor;
    private final TaskRepository taskRepository;

    @Override
    @KafkaListener(topics = "tasks", groupId = "task-group")
    public void consume(Task task) {
        task.setStatus(TaskStatus.PROCESSING);

        taskRepository.save(task);

        taskProcessor.process(task)
                .thenAccept(taskRepository::save);
    }
}
