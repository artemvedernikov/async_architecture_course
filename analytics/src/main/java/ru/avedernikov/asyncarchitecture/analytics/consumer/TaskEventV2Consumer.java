package ru.avedernikov.asyncarchitecture.analytics.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.avedernikov.asyncarchitecture.analytics.repository.TaskRepository;
import ru.avedernikov.asyncarchitecture.eventmodel.task.TaskV2Event;

// CUD events
@Component
public class TaskEventV2Consumer {

    @Autowired
    private TaskRepository taskRepository;

    @KafkaListener(topics = "${spring.kafka.task-streaming-v2-topic-name}")
    public void taskV2EventListener(TaskV2Event taskV2Event) {
        // update task
        // save to db
    }
}
