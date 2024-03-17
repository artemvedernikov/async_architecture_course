package ru.avedernikov.asyncarchitecture.accountingbilling.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.avedernikov.asyncarchitecture.accountingbilling.repository.AccountRepository;
import ru.avedernikov.asyncarchitecture.eventmodel.account.AccountV1Event;
import ru.avedernikov.asyncarchitecture.eventmodel.task.TaskV2Event;

// CUD
@Component
public class AccountStreamingV1EventConsumer {
    @Autowired
    private AccountRepository accountRepository;

    @KafkaListener(
            topics = "account-streaming-v1",
            containerFactory = "kafkaListenerContainerFactory")
    public void accountV1EventListener(AccountV1Event accountV1Event) {
        // update account
        // save to db
    }
}
