package ru.avedernikov.asyncarchitecture.analytics.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.avedernikov.asyncarchitecture.analytics.model.BusinessBalanceTransaction;
import ru.avedernikov.asyncarchitecture.analytics.repository.BusinessBalanceTransactionRepository;
import ru.avedernikov.asyncarchitecture.eventmodel.BusinessBalanceChangedV1Event;

@Component
public class BusinessBalanceChangedV1EventConsumer {

    @Autowired
    private BusinessBalanceTransactionRepository businessBalanceTransactionRepository;

    @KafkaListener(topics = "${spring.kafka.business-balance-changed-v1-topic-name}")
    public void taskCostAssignedV1EventListener(BusinessBalanceChangedV1Event businessBalanceChangedV1Event) {
        BusinessBalanceTransaction transaction = new BusinessBalanceTransaction(businessBalanceChangedV1Event.getBalanceChange());
        businessBalanceTransactionRepository.save(transaction);
    }
}
