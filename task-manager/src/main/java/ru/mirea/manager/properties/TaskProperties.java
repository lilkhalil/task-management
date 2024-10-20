package ru.mirea.manager.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "tasks")
@Data
public class TaskProperties {
    private double completionProbability;
}
