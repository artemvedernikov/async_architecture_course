package ru.avedernikov.asyncarchitecture.analytics.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.avedernikov.asyncarchitecture.analytics.repository.TaskRepository;
import ru.avedernikov.asyncarchitecture.eventmodel.task.TaskCostAssignedV1Event;

@Component
public class TaskCostAssignedV1EventConsumer {

    @Autowired
    private TaskRepository taskRepository;

    @KafkaListener(topics = "${spring.kafka.task-cost-assigned-v1-topic-name}")
    public void taskCostAssignedV1EventListener(TaskCostAssignedV1Event taskCostAssignedV1Event) {

    }

}
