package ru.mirea.worker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import ru.mirea.domain.configuration.DomainConfiguration;

@SpringBootApplication
@Import(DomainConfiguration.class)
public class TaskWorkerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaskWorkerApplication.class, args);
    }

}
