package ru.avedernikov.asyncarchitecture.accountingbilling.cron;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.avedernikov.asyncarchitecture.accountingbilling.model.BillingCycle;
import ru.avedernikov.asyncarchitecture.accountingbilling.model.Transaction;
import ru.avedernikov.asyncarchitecture.accountingbilling.model.TransactionType;
import ru.avedernikov.asyncarchitecture.accountingbilling.repository.BillingCycleRepository;
import ru.avedernikov.asyncarchitecture.accountingbilling.repository.TransactionRepository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class ScheduledBillingCycleService {

    @Autowired
    private BillingCycleRepository billingCycleRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Scheduled(cron = "0 0 * * * *")
    public void closeBillingCycles() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime startInterval = now.minusDays(1);
        List<Transaction> transactionsInInterval = transactionRepository.getInInterval(startInterval.toInstant(), now.toInstant());

        if (dayBalance > 0) {
            Transaction transaction = new Transaction(task, task.getAssignee(), currentUserBillingCycle, reward, TransactionType.PAYMENT,  new Date());
            transactionRepository.save(transaction);
        }

    }
}
