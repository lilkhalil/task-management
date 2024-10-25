package ru.mirea.manager.consumer.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.mirea.manager.consumer.TaskConsumer;
import ru.mirea.manager.mapper.TaskMapper;
import ru.mirea.manager.repository.TaskRepository;
import ru.mirea.model.TaskMessage;

@Service
@RequiredArgsConstructor
public class TaskConsumerImpl implements TaskConsumer {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    @KafkaListener(topics = "${tasks.topic-name-persisting}", groupId = "${spring.kafka.consumer.group-id}")
    @Override
    public void consume(TaskMessage taskMessage) {
        taskRepository.save(taskMapper.taskMessageToTask(taskMessage));
    }

}
