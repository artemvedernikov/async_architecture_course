package ru.avedernikov.asyncarchitecture.accountingbilling.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.avedernikov.asyncarchitecture.accountingbilling.dto.AccountBillingStateDTO;
import ru.avedernikov.asyncarchitecture.accountingbilling.dto.CompanyDailyStatsDTO;
import ru.avedernikov.asyncarchitecture.accountingbilling.model.Transaction;
import ru.avedernikov.asyncarchitecture.accountingbilling.repository.AccountRepository;
import ru.avedernikov.asyncarchitecture.accountingbilling.repository.TaskRepository;
import ru.avedernikov.asyncarchitecture.accountingbilling.repository.TransactionRepository;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/billing")
public class BillingController {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TaskRepository taskBillingRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    // todo: pass user_id through oauth
    @GetMapping("/")
    ResponseEntity<AccountBillingStateDTO> getBillingState(String userPublicId) {
        List<Transaction> userTransactions = transactionRepository.getByAssigneeId(userPublicId);


    }

    // todo: validate for managers/admins oauth
    @GetMapping("/daily")
    ResponseEntity<CompanyDailyStatsDTO> getDailyStats() {

    }

}
