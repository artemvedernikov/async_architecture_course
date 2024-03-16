package ru.avedernikov.asyncarchitecture.accountingbilling.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.avedernikov.asyncarchitecture.accountingbilling.repository.AccountRepository;

@Component
public class AccountStreamingEventConsumer {
    @Autowired
    private AccountRepository accountRepository;
}
