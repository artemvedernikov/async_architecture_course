package ru.avedernikov.asyncarchitecture.analytics.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.avedernikov.asyncarchitecture.eventmodel.task.TaskCostAssignedV1Event;

@Component
public class TaskCreatedV1EventConsumer {

    @KafkaListener(topics = "${spring.kafka.created-v1-topic-name}")
    public void taskCostAssignedV1EventListener(TaskCostAssignedV1Event taskCostAssignedV1Event) {

    }

}
