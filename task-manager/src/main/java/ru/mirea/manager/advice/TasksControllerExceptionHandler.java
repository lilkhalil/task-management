package ru.mirea.manager.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.mirea.manager.api.TasksApiController;
import ru.mirea.manager.dto.ErrorDto;
import ru.mirea.manager.entity.enums.TaskStatus;

import java.util.EnumSet;
import java.util.stream.Collectors;

@RestControllerAdvice(basePackageClasses = {TasksApiController.class})
public class TasksControllerExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorDto> handleInvalidStatus() {
        String availableValues = EnumSet.allOf(TaskStatus.class)
                .stream()
                .map(Enum::name)
                .collect(Collectors.joining(", "));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorDto.builder()
                        .code(400)
                        .message("Неизвестный статус задачи. Возможные значения [%s]".formatted(availableValues))
                        .build());
    }

}
