package ru.avedernikov.asyncarchitecture.accountingbilling.cron;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.avedernikov.asyncarchitecture.accountingbilling.repository.BillingCycleRepository;
import ru.avedernikov.asyncarchitecture.accountingbilling.repository.TransactionRepository;

@Service
public class ScheduledBillingCycleService {

    @Autowired
    private BillingCycleRepository billingCycleRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Scheduled(cron = "0 0 * * * *")
    public void closeBillingCycles() {

    }
}
