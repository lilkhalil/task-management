package ru.mirea.manager.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mirea.domain.entity.Task;
import ru.mirea.domain.entity.enums.TaskStatus;
import ru.mirea.domain.repository.TaskRepository;
import ru.mirea.manager.dto.TaskRqDto;
import ru.mirea.manager.exception.TaskNotFoundException;
import ru.mirea.manager.producer.KafkaProducer;
import ru.mirea.manager.service.TaskService;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final KafkaProducer kafkaProducer;

    @Override
    public List<Task> findAll(TaskStatus status) {
        return taskRepository.findByStatus(status);
    }

    @Override
    public Task findById(Long taskId) {
        return taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException(taskId));
    }

    @Override
    public Task createTask(TaskRqDto requestBody) {
        Task task = Task.builder()
                .title(requestBody.getTitle())
                .description(requestBody.getDescription())
                .createdAt(LocalDateTime.now())
                .status(TaskStatus.NEW)
                .build();

        taskRepository.saveAndFlush(task);

        kafkaProducer.sendMessage(task);

        return task;
    }
}
