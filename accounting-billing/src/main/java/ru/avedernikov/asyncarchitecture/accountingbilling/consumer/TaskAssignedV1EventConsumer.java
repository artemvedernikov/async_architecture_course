package ru.avedernikov.asyncarchitecture.accountingbilling.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import ru.avedernikov.asyncarchitecture.accountingbilling.model.Account;
import ru.avedernikov.asyncarchitecture.accountingbilling.model.Task;
import ru.avedernikov.asyncarchitecture.accountingbilling.model.Transaction;
import ru.avedernikov.asyncarchitecture.accountingbilling.repository.AccountRepository;
import ru.avedernikov.asyncarchitecture.accountingbilling.repository.TaskRepository;
import ru.avedernikov.asyncarchitecture.accountingbilling.repository.TransactionRepository;
import ru.avedernikov.asyncarchitecture.eventmodel.BusinessBalanceChangedV1Event;
import ru.avedernikov.asyncarchitecture.eventmodel.task.TaskAssignedV1Event;

import java.util.Date;
import java.util.Optional;

@Component
public class TaskAssignedV1EventConsumer {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private KafkaTemplate<String, BusinessBalanceChangedV1Event> businessBalanceChangedV1EventProducer;

    @Value(value = "${spring.kafka.business-balance-changed-v1-topic-name}")
    private String businessBalanceChangedV1TopicName;

    @KafkaListener(
            topics = "${spring.kafka.task-assigned-v1-topic-name}",
            containerFactory = "taskAssignedV1EventContainerFactory")
    public void taskAssignedV1EventListener(TaskAssignedV1Event taskAssignedV1Event) {
        Optional<Account> assigneeOpt = accountRepository.findOneByPublicId(taskAssignedV1Event.getAssigneePublicId());
        Optional<Task> taskOpt = taskRepository.findOneByPublicId(taskAssignedV1Event.getTaskPublicId());
        if (assigneeOpt.isPresent() && taskOpt.isPresent()) {
            Account assignee = assigneeOpt.get();
            Task task = taskOpt.get();
            double cost = task.getCost();

            task.setAssignee(assignee);
            taskRepository.save(task);

            Transaction transaction = new Transaction(task, assignee, -cost, new Date());
            transactionRepository.save(transaction);

            BusinessBalanceChangedV1Event businessBalanceChangedV1Event = new BusinessBalanceChangedV1Event(cost);
            businessBalanceChangedV1EventProducer.send(businessBalanceChangedV1TopicName, businessBalanceChangedV1Event);
        } else {
            // todo: send to dead letters
        }
    }
}
