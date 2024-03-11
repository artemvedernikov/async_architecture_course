package ru.avedernikov.asyncarchitecture.tasktracker.config;

import org.springframework.beans.factory.annotation.Value;

public class TaskBusinessEventProducerConfig {
    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;

    @Value(value = "${spring.kafka.task-business-events-topic-name}")
    private String taskBusinessEventsTopicName;
}
