package ru.mirea.domain.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "ru.mirea.domain.repository")
@EntityScan(basePackages = "ru.mirea.domain.entity")
public class DomainConfiguration {

}
