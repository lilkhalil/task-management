package ru.mirea.manager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TaskNotFoundException extends RuntimeException {

    private static final String msg = "Task with id=%d does not found!";

    public TaskNotFoundException(Long taskId) {
        super(msg.formatted(taskId));
    }
}
