package ru.avedernikov.asyncarchitecture.accountingbilling.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.avedernikov.asyncarchitecture.accountingbilling.repository.TaskRepository;
import ru.avedernikov.asyncarchitecture.eventmodel.task.TaskV2Event;

// CUD
@Component
public class TaskStreamingV2EventConsumer {

    @Autowired
    private TaskRepository taskRepository;

    @KafkaListener(
            topics = "task-streaming-v2",
            containerFactory = "kafkaListenerContainerFactory")
    public void taskV2EventListener(TaskV2Event taskV2Event) {
        // update task
        // save to db
    }
}
