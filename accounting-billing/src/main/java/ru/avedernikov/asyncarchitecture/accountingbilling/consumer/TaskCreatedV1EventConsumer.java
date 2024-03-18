package ru.avedernikov.asyncarchitecture.accountingbilling.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import ru.avedernikov.asyncarchitecture.accountingbilling.model.*;
import ru.avedernikov.asyncarchitecture.accountingbilling.repository.AccountRepository;
import ru.avedernikov.asyncarchitecture.accountingbilling.repository.BillingCycleRepository;
import ru.avedernikov.asyncarchitecture.accountingbilling.repository.TaskRepository;
import ru.avedernikov.asyncarchitecture.accountingbilling.repository.TransactionRepository;
import ru.avedernikov.asyncarchitecture.accountingbilling.utils.TaskPriceGenerator;
import ru.avedernikov.asyncarchitecture.eventmodel.BusinessBalanceChangedV1Event;
import ru.avedernikov.asyncarchitecture.eventmodel.task.TaskCostAssignedV1Event;
import ru.avedernikov.asyncarchitecture.eventmodel.task.TaskCreatedV1Event;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

@Component
public class TaskCreatedV1EventConsumer {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private BillingCycleRepository billingCycleRepository;

    @Autowired
    private KafkaTemplate<String, TaskCostAssignedV1Event> taskCostAssignedV1Producer;

    @Value(value = "${spring.kafka.task-cost-assigned-v1-topic-name}")
    private String taskCostAssignedV1TopicName;

    @Autowired
    private KafkaTemplate<String, BusinessBalanceChangedV1Event> businessBalanceChangedV1EventProducer;

    @Value(value = "${spring.kafka.business-balance-changed-v1-topic-name}")
    private String businessBalanceChangedV1TopicName;

    @KafkaListener(topics = "${spring.kafka.task-created-v1-topic-name}")
    public void taskCreatedV1Listener(TaskCreatedV1Event taskCreatedV1Event) {
        Optional<Account> assigneeOpt = accountRepository.findOneByPublicId(taskCreatedV1Event.getAssigneePublicId());
        String taskPublicId = taskCreatedV1Event.getTaskPublicId();
        Optional<Task> taskOpt = taskRepository.findOneByPublicId(taskPublicId);
        if (assigneeOpt.isPresent()) {
            Account assignee = assigneeOpt.get();
            Task task;
            if (taskOpt.isPresent()) {
                task = taskOpt.get();
            } else {
                double cost = TaskPriceGenerator.generateTaskCost();
                double reward = TaskPriceGenerator.generateTaskReward();
                task = new Task(taskPublicId, cost, reward, assignee);
                TaskCostAssignedV1Event taskCostAssignedV1Event = new TaskCostAssignedV1Event(
                        new TaskCostAssignedV1Event.TaskCostAssignedV1EventMeta(),
                        new TaskCostAssignedV1Event.TaskCostAssignedV1EventData(taskPublicId, cost, reward)
                );
                taskCostAssignedV1Producer.send(taskCostAssignedV1TopicName, taskCostAssignedV1Event);
            }
            double cost = task.getCost();

            task.setAssignee(assignee);
            taskRepository.save(task);

            BillingCycle currentUserBillingCycle = billingCycleRepository.getOrCreateOpenBillingCycle(task.getAssignee(), LocalDate.now());
            Transaction transaction = new Transaction(task, assignee, currentUserBillingCycle, -cost, TransactionType.ENROLLMENT, new Date());
            transactionRepository.save(transaction);

            BusinessBalanceChangedV1Event businessBalanceChangedV1Event = new BusinessBalanceChangedV1Event(cost);
            businessBalanceChangedV1EventProducer.send(businessBalanceChangedV1TopicName, businessBalanceChangedV1Event);
        } else {
            // todo: send to dead letters
        }
    }

}
