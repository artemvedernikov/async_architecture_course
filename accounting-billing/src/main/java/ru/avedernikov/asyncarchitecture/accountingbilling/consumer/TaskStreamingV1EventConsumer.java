package ru.avedernikov.asyncarchitecture.accountingbilling.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.avedernikov.asyncarchitecture.accountingbilling.repository.TaskRepository;
import ru.avedernikov.asyncarchitecture.eventmodel.account.AccountV1Event;
import ru.avedernikov.asyncarchitecture.eventmodel.task.TaskV1Event;

// CUD
@Component
public class TaskStreamingV1EventConsumer {
    @Autowired
    private TaskRepository taskRepository;

    @KafkaListener(
            topics = "task-streaming-v1",
            containerFactory = "kafkaListenerContainerFactory")
    public void taskV1EventListener(TaskV1Event taskV1Event) {
        // update task
        // save to db
    }

}
