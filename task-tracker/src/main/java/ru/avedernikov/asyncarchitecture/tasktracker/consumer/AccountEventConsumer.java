package ru.avedernikov.asyncarchitecture.tasktracker.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.avedernikov.asyncarchitecture.eventmodel.account.AccountEvent;
import ru.avedernikov.asyncarchitecture.tasktracker.model.AccountRole;
import ru.avedernikov.asyncarchitecture.tasktracker.repository.AccountRepository;

import java.util.UUID;

@Component
public class AccountEventConsumer {

    @Autowired
    private AccountRepository accountRepository;

    @KafkaListener(
            topics = "accounts-event",
            containerFactory = "kafkaListenerContainerFactory")
    public void greetingListener(AccountEvent accountEvent) {
        System.out.println("Got event");
        UUID id = accountEvent.getAccountId();
        String email = accountEvent.getEmail();
        String roleStr = accountEvent.getRole();
        AccountRole role = AccountRole.valueOf(roleStr);
        accountRepository.upsert(id, email, role);
    }
}
