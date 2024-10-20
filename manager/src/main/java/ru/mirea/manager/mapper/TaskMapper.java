package ru.mirea.manager.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ru.mirea.manager.domain.Task;
import ru.mirea.manager.dto.TaskDto;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TaskMapper {
    TaskDto taskToTaskDto(Task task);
}
