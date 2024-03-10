package ru.avedernikov.asyncarchitecture.accountingbilling.controller.billing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.avedernikov.asyncarchitecture.accountingbilling.dto.billing.AccountBillingStateDTO;
import ru.avedernikov.asyncarchitecture.accountingbilling.dto.billing.DailyStatsDTO;
import ru.avedernikov.asyncarchitecture.accountingbilling.model.Transaction;
import ru.avedernikov.asyncarchitecture.accountingbilling.repository.AccountBillingRepository;
import ru.avedernikov.asyncarchitecture.accountingbilling.repository.TaskRepository;
import ru.avedernikov.asyncarchitecture.accountingbilling.repository.TransactionRepository;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/billing")
public class BillingController {

    @Autowired
    private AccountBillingRepository accountBillingRepository;

    @Autowired
    private TaskRepository taskBillingRepository;


    @Autowired
    private TransactionRepository transactionRepository;

    // todo: pass user_id through oauth
    @GetMapping("/")
    ResponseEntity<AccountBillingStateDTO> getBillingState(UUID userId) {
        List<Transaction> userTransactions = transactionRepository.getByAssigneeId(userId);


    }

    // todo: validate for managers/admins oauth
    @GetMapping("/daily")
    ResponseEntity<DailyStatsDTO> getDailyStats() {

    }

}
