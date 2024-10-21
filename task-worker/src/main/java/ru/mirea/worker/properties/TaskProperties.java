package ru.mirea.worker.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "tasks")
@Data
public class TaskProperties {
    private String topicName;
    private double completionProbability;
    private long lowerBound;
    private long upperBound;
}
