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
            topics = "account-event",
            containerFactory = "kafkaListenerContainerFactory")
    public void greetingListener(AccountEvent accountEvent) {
        System.out.println("Got event");
        if (accountEvent.getType().equals("CLIENT_REGISTER") || accountEvent.getType().equals("CLIENT_UPDATE")) {
            UUID id = UUID.fromString(accountEvent.getUserId());
            String email = accountEvent.getDetails().get("email");
            // todo
            String roleStr = accountEvent.getDetails().get("role");
            AccountRole role = AccountRole.valueOf(roleStr);
            accountRepository.upsert(id, email, role);
        } else if (accountEvent.getType().equals("DELETE_ACCOUNT")) {
            UUID id = UUID.fromString(accountEvent.getUserId());
            accountRepository.deleteById(id);
        }
    }
}
