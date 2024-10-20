package ru.mirea.manager.service.impl;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mirea.domain.entity.enums.TaskStatus;
import ru.mirea.manager.properties.TaskProperties;
import ru.mirea.manager.service.TaskStatusService;

@Service
public class TaskStatusServiceImpl implements TaskStatusService {

    @Setter(onMethod_ = @Autowired)
    private TaskProperties taskProperties;

    @Override
    public TaskStatus nextStatus(TaskStatus status) {
        return switch (status) {
            case NEW:
                yield TaskStatus.PROCESSING;
            case PROCESSING:
                yield Math.random() < taskProperties.getCompletionProbability() ? TaskStatus.COMPLETED : TaskStatus.FAILED;
            case COMPLETED:
            case FAILED:
                yield status;
        };
    }
}
