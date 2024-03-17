package ru.avedernikov.asyncarchitecture.analytics.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.avedernikov.asyncarchitecture.analytics.repository.TaskRepository;
import ru.avedernikov.asyncarchitecture.eventmodel.task.TaskV1Event;

// CUD events
@Component
public class TaskEventV1Consumer {

    @Autowired
    private TaskRepository taskRepository;

    @KafkaListener(topics = "${spring.kafka.task-streaming-v1-topic-name}")
    public void taskV1EventListener(TaskV1Event taskV1Event) {
        // update task
        // save to db
    }

}
