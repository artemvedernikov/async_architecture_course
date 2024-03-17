package ru.avedernikov.asyncarchitecture.analytics.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.avedernikov.asyncarchitecture.analytics.model.Task;
import ru.avedernikov.asyncarchitecture.analytics.repository.TaskRepository;
import ru.avedernikov.asyncarchitecture.eventmodel.task.TaskCostAssignedV1Event;

import java.util.Optional;

@Component
public class TaskCostAssignedV1EventConsumer {

    @Autowired
    private TaskRepository taskRepository;

    @KafkaListener(topics = "${spring.kafka.task-cost-assigned-v1-topic-name}")
    public void taskCostAssignedV1EventListener(TaskCostAssignedV1Event taskCostAssignedV1Event) {
        String taskPublicId = taskCostAssignedV1Event.getData().getTaskPublicId();
        Optional<Task> taskOpt = taskRepository.findByPublicId(taskPublicId);
        Task task;
        if (taskOpt.isPresent()) {
            task = taskOpt.get();
        } else {
            task = new Task();
        }
        task.setCost(taskCostAssignedV1Event.getData().getCost());
        task.setReward(taskCostAssignedV1Event.getData().getReward());
        taskRepository.save(task);
    }

}
