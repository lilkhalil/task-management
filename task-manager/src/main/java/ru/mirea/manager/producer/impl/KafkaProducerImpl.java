package ru.mirea.manager.producer.impl;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.mirea.domain.entity.Task;
import ru.mirea.manager.producer.KafkaProducer;

@Service
@RequiredArgsConstructor
public class KafkaProducerImpl implements KafkaProducer {

    private final KafkaTemplate<String, Task> kafkaTemplate;

    @Setter(onMethod_ = @Value("${tasks.topic-name}"))
    private String topic;

    @Override
    public void sendMessage(Task task) {
        kafkaTemplate.send(topic, task);
    }
}
