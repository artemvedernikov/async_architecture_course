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
import ru.avedernikov.asyncarchitecture.eventmodel.task.TaskAssignedV1Event;
import ru.avedernikov.asyncarchitecture.eventmodel.task.TaskCostAssignedV1Event;

import java.time.LocalDate;
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
    private BillingCycleRepository billingCycleRepository;

    @Autowired
    private KafkaTemplate<String, BusinessBalanceChangedV1Event> businessBalanceChangedV1EventProducer;

    @Value(value = "${spring.kafka.business-balance-changed-v1-topic-name}")
    private String businessBalanceChangedV1TopicName;

    @Autowired
    private KafkaTemplate<String, TaskCostAssignedV1Event> taskCostAssignedV1Producer;

    @Value(value = "${spring.kafka.task-cost-assigned-v1-topic-name}")
    private String taskCostAssignedV1TopicName;


    @KafkaListener(
            topics = "${spring.kafka.task-assigned-v1-topic-name}",
            containerFactory = "taskAssignedV1EventContainerFactory")
    public void taskAssignedV1EventListener(TaskAssignedV1Event taskAssignedV1Event) {
        Optional<Account> assigneeOpt = accountRepository.findOneByPublicId(taskAssignedV1Event.getAssigneePublicId());
        String taskPublicId = taskAssignedV1Event.getTaskPublicId();
        Optional<Task> taskOpt = taskRepository.findOneByPublicId(taskPublicId);
        if (assigneeOpt.isPresent()) {
            Account assignee = assigneeOpt.get();
            Task task;
            if (taskOpt.isPresent()) {
                task = taskOpt.get();
            } else {
                // creating task without title etc
                double cost = TaskPriceGenerator.generateTaskCost();
                double reward = TaskPriceGenerator.generateTaskReward();
                task = new Task(taskPublicId, cost, reward, assignee);
                TaskCostAssignedV1Event taskCostAssignedV1Event = new TaskCostAssignedV1Event(
                        new TaskCostAssignedV1Event.TaskCostAssignedV1EventMeta(),
                        new TaskCostAssignedV1Event.TaskCostAssignedV1EventData(taskPublicId, cost, reward)
                );
                taskCostAssignedV1Producer.send(taskCostAssignedV1TopicName, taskCostAssignedV1Event);
            }

            Account assignee = assigneeOpt.get();
            double cost = task.getCost();

            task.setAssignee(assignee);
            taskRepository.save(task);

            BillingCycle currentUserBillingCycle = billingCycleRepository.getOrCreateOpenBillingCycle(assignee, LocalDate.now());
            Transaction transaction = new Transaction(task, assignee, currentUserBillingCycle, -cost, TransactionType.ENROLLMENT, new Date());
            transactionRepository.save(transaction);

            BusinessBalanceChangedV1Event businessBalanceChangedV1Event = new BusinessBalanceChangedV1Event(cost);
            businessBalanceChangedV1EventProducer.send(businessBalanceChangedV1TopicName, businessBalanceChangedV1Event);
        } else {
            // todo: send to dead letters
        }
    }
}
