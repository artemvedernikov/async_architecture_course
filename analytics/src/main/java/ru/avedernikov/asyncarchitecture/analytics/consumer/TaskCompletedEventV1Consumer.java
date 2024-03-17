package ru.avedernikov.asyncarchitecture.analytics.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.avedernikov.asyncarchitecture.analytics.repository.TaskRepository;
import ru.avedernikov.asyncarchitecture.eventmodel.task.TaskCompletedV1Event;

@Component
public class TaskCompletedEventV1Consumer {

    @Autowired
    private TaskRepository taskRepository;

    @KafkaListener(topics = "${spring.kafka.task-completed-v1-topic-name}")
    public void taskCompletedV1EventListener(TaskCompletedV1Event taskCompletedV1Event) {

    }

}
