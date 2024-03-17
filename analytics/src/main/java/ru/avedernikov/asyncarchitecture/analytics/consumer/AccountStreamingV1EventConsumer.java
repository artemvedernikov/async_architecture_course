package ru.avedernikov.asyncarchitecture.analytics.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.avedernikov.asyncarchitecture.analytics.repository.AccountRepository;
import ru.avedernikov.asyncarchitecture.eventmodel.account.AccountV1Event;

import java.util.UUID;

@Component
public class AccountStreamingV1EventConsumer {

    @Autowired
    private AccountRepository accountRepository;

    @KafkaListener(
            topics = "${spring.kafka.account-streaming-v1-topic-name}",
            containerFactory = "kafkaListenerContainerFactory")
    public void accountV1EventListener(AccountV1Event accountEvent) {
        // update account
        // save to db
    }
}
