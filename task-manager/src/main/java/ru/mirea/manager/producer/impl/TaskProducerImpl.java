package ru.mirea.manager.producer.impl;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.mirea.manager.producer.TaskProducer;
import ru.mirea.model.TaskMessage;

@Service
@RequiredArgsConstructor
public class TaskProducerImpl implements TaskProducer {

    private final KafkaTemplate<String, TaskMessage> kafkaTemplate;

    @Setter(onMethod_ = @Value("${tasks.topic-name-processing}"))
    private String topic;

    @Override
    public void sendMessage(TaskMessage task) {
        kafkaTemplate.send(topic, task);
    }
}
