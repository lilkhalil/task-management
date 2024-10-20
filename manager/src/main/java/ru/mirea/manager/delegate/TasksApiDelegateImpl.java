package ru.mirea.manager.delegate;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.mirea.domain.entity.Task;
import ru.mirea.domain.entity.enums.TaskStatus;
import ru.mirea.manager.api.TasksApiDelegate;
import ru.mirea.manager.dto.TaskDto;
import ru.mirea.manager.dto.TaskRqDto;
import ru.mirea.manager.mapper.TaskMapper;
import ru.mirea.manager.service.TaskService;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TasksApiDelegateImpl implements TasksApiDelegate {

    private final TaskService taskService;
    private final TaskMapper taskMapper;

    @Override
    public ResponseEntity<TaskDto> createTask(TaskRqDto taskRqDto) {
        Task task = taskService.createTask(taskRqDto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(task.getId())
                .toUri();

        return ResponseEntity.created(location)
                .body(taskMapper.taskToTaskDto(task));
    }

    @Override
    public ResponseEntity<TaskDto> getTask(Long taskId) {
        Task task = taskService.findById(taskId);
        return ResponseEntity.ok(taskMapper.taskToTaskDto(task));
    }

    @Override
    public ResponseEntity<List<TaskDto>> getTasks(Optional<String> status) {
        return taskService.findAll(status
                        .map(String::toUpperCase)
                        .map(TaskStatus::valueOf)
                        .orElse(null)).stream()
                .map(taskMapper::taskToTaskDto)
                .collect(Collectors.collectingAndThen(Collectors.toList(), ResponseEntity::ok));
    }
}
