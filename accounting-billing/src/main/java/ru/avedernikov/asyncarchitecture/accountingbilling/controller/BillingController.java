package ru.avedernikov.asyncarchitecture.accountingbilling.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.avedernikov.asyncarchitecture.accountingbilling.dto.AccountBillingStateDTO;
import ru.avedernikov.asyncarchitecture.accountingbilling.dto.CompanyDailyStatsDTO;
import ru.avedernikov.asyncarchitecture.accountingbilling.model.Transaction;
import ru.avedernikov.asyncarchitecture.accountingbilling.repository.AccountRepository;
import ru.avedernikov.asyncarchitecture.accountingbilling.repository.TransactionRepository;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/v1/billing")
public class BillingController {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    // todo: pass user_id through oauth
    @GetMapping("/")
    ResponseEntity<AccountBillingStateDTO> getBillingState(String userPublicId) {
        List<Transaction> userTransactions = transactionRepository.getByAssigneeId(userPublicId);
        List<AccountBillingStateDTO.TransactionDTO> transactionDTOs = userTransactions.stream().map(t -> new AccountBillingStateDTO.TransactionDTO(
                t.getDate(),
                t.getAmount()
        )).toList();

        AccountBillingStateDTO dto = new AccountBillingStateDTO(
                LocalDate.now(),

                userPublicId,
                transactionDTOs
        );
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    // todo: validate for managers/admins oauth
    @GetMapping("/daily")
    ResponseEntity<CompanyDailyStatsDTO> getDailyStats() {

    }

}
