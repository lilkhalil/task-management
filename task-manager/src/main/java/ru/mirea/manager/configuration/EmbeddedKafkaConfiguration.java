package ru.mirea.manager.configuration;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.EmbeddedKafkaZKBroker;

@Configuration
@Profile("dev")
public class EmbeddedKafkaConfiguration {

    @Setter(onMethod_ = @Value("${tasks.topic-name}"))
    private String topic;

    @Bean
    public EmbeddedKafkaBroker broker() {
        return new EmbeddedKafkaZKBroker(1, false, topic)
                .kafkaPorts(9092)
                .brokerListProperty("spring.kafka.bootstrap-servers");
    }
}
