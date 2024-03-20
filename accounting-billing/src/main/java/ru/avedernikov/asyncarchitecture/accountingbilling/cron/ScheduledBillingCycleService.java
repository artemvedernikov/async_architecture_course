package ru.avedernikov.asyncarchitecture.accountingbilling.cron;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.avedernikov.asyncarchitecture.accountingbilling.model.Account;
import ru.avedernikov.asyncarchitecture.accountingbilling.model.BillingCycle;
import ru.avedernikov.asyncarchitecture.accountingbilling.model.Transaction;
import ru.avedernikov.asyncarchitecture.accountingbilling.model.TransactionType;
import ru.avedernikov.asyncarchitecture.accountingbilling.repository.BillingCycleRepository;
import ru.avedernikov.asyncarchitecture.accountingbilling.repository.TransactionRepository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class ScheduledBillingCycleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduledBillingCycleService.class);

    @Autowired
    private BillingCycleRepository billingCycleRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Scheduled(cron = "0 0 * * * *")
    public void closeBillingCycles() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime startInterval = now.minusDays(1);
        List<Transaction> transactionsInInterval = transactionRepository.getInInterval(startInterval.toInstant(), now.toInstant());

        if (balance > 0) {
            boolean paymentResultSuccess = payThroughSomeGateway(account, );

            if (paymentResultSuccess) {
                Transaction transaction = new Transaction(task, task.getAssignee(), currentUserBillingCycle, reward, TransactionType.PAYMENT,  new Date());
                transactionRepository.save(transaction);
            } else {
                // retry
            }
            // cleanup balance
        }

    }


    public boolean payThroughSomeGateway(Account account, double amount) {
        // todo: real payment service call like stripe
        boolean paymentSuccess = ThreadLocalRandom.current().nextBoolean();
        if (paymentSuccess) {
            LOGGER.info("Successfully payed amount {} to user {}", account.getPublicId(), amount);
        } else {
            LOGGER.error("Failed to pay amount {} to user {}", account.getPublicId(), amount);
        }
        return paymentSuccess;
    }
}
