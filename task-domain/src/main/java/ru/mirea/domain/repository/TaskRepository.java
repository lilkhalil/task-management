package ru.mirea.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.mirea.domain.entity.Task;
import ru.mirea.domain.entity.enums.TaskStatus;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query("SELECT t FROM Task t WHERE (:status IS NULL OR t.status = :status)")
    List<Task> findByStatus(@Param("status") TaskStatus taskStatus);
}
