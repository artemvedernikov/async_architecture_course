package ru.avedernikov.asyncarchitecture.accountingbilling.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.avedernikov.asyncarchitecture.accountingbilling.repository.TaskRepository;
import ru.avedernikov.asyncarchitecture.eventmodel.account.AccountV1Event;

@Component
public class TaskEventConsumer {
    @Autowired
    private TaskRepository taskRepository;

    @KafkaListener(
            topics = "task-streaming",
            containerFactory = "kafkaListenerContainerFactory")
    public void taskEventListener(AccountV1Event accountEvent) {

    }

}
