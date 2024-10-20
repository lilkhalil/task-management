package ru.mirea.manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mirea.manager.domain.Task;
import ru.mirea.manager.domain.enums.TaskStatus;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByStatus(TaskStatus taskStatus);
}
