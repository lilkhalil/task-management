package ru.mirea.manager.producer;

import ru.mirea.domain.entity.Task;

public interface KafkaProducer {
    void sendMessage(Task task);
}
