package ru.avedernikov.asyncarchitecture.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.avedernikov.asyncarchitecture.auth.AccountRepository;
import ru.avedernikov.asyncarchitecture.auth.model.Account;
import ru.avedernikov.asyncarchitecture.auth.utils.AccountEventConverter;
import ru.avedernikov.asyncarchitecture.eventmodel.account.AccountEvent;

import java.util.UUID;

@RestController
@RequestMapping("/v1/auth")
public class AuthController {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private KafkaTemplate<UUID, AccountEvent> accountEventTemplate;

    @PostMapping("/sign_in")
    signIn() {
        // todo
        Account account = null;
        AccountEvent event = AccountEventConverter.accountToAccountEvent(account);
        accountEventTemplate.send(, event);
    }

}
