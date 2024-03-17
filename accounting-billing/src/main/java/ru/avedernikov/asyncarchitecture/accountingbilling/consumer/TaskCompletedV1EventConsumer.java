package ru.avedernikov.asyncarchitecture.accountingbilling.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import ru.avedernikov.asyncarchitecture.accountingbilling.model.Task;
import ru.avedernikov.asyncarchitecture.accountingbilling.model.Transaction;
import ru.avedernikov.asyncarchitecture.accountingbilling.repository.TaskRepository;
import ru.avedernikov.asyncarchitecture.accountingbilling.repository.TransactionRepository;
import ru.avedernikov.asyncarchitecture.eventmodel.BusinessBalanceChangedV1Event;
import ru.avedernikov.asyncarchitecture.eventmodel.task.TaskCompletedV1Event;

import java.util.Date;
import java.util.Optional;

@Component
public class TaskCompletedV1EventConsumer {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private KafkaTemplate<String, BusinessBalanceChangedV1Event> businessBalanceChangedV1EventProducer;

    @Value(value = "${spring.kafka.business-balance-changed-v1-topic-name}")
    private String businessBalanceChangedV1TopicName;

    @KafkaListener(topics = "${spring.kafka.task-completed-v1-topic-name}")
    void taskCompletedV1EventListener(TaskCompletedV1Event taskCompletedV1Event) {
        Optional<Task> taskOpt = taskRepository.findOneByPublicId(taskCompletedV1Event.getTaskPublicId());
        if (taskOpt.isPresent()) {
            Task task = taskOpt.get();
            double reward = task.getReward();

            Transaction transaction = new Transaction(task, task.getAssignee(), reward, new Date());
            transactionRepository.save(transaction);

            BusinessBalanceChangedV1Event businessBalanceChangedV1Event = new BusinessBalanceChangedV1Event(-reward);
            businessBalanceChangedV1EventProducer.send(businessBalanceChangedV1TopicName, businessBalanceChangedV1Event);
        } else {
            // todo: send to dead letters
        }

    }

}
