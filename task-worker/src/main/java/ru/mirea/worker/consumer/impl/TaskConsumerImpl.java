package ru.mirea.worker.consumer.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.mirea.domain.entity.Task;
import ru.mirea.worker.consumer.TaskConsumer;
import ru.mirea.worker.processor.TaskProcessor;

@Service
@RequiredArgsConstructor
@Slf4j
public class TaskConsumerImpl implements TaskConsumer {

    private final TaskProcessor taskProcessor;

    @Override
    @KafkaListener(topics = "${tasks.topic-name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(Task task) {
        taskProcessor.process(task);
    }
}
