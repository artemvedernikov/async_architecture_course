package ru.avedernikov.asyncarchitecture.analytics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.avedernikov.asyncarchitecture.analytics.dto.DailyEarningsDTO;
import ru.avedernikov.asyncarchitecture.analytics.dto.MostExpensiveTaskDTO;
import ru.avedernikov.asyncarchitecture.analytics.dto.MostExpensiveTaskInterval;
import ru.avedernikov.asyncarchitecture.analytics.dto.NegativeBalanceDTO;
import ru.avedernikov.asyncarchitecture.analytics.repository.AccountRepository;
import ru.avedernikov.asyncarchitecture.analytics.repository.BusinessBalanceTransactionRepository;
import ru.avedernikov.asyncarchitecture.analytics.repository.TaskRepository;

import java.time.LocalDate;

@RestController
@RequestMapping("/v1/analytics")
public class AnalyticsController {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TaskRepository taskBillingRepository;

    @Autowired
    private BusinessBalanceTransactionRepository businessBalanceTransactionRepository;

    @GetMapping("/daily_earnings")
    public ResponseEntity<DailyEarningsDTO> dailyEarnings() {
        LocalDate today = LocalDate.now();

        DailyEarningsDTO dto = new DailyEarningsDTO();
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/negative_balance_workers")
    public ResponseEntity<NegativeBalanceDTO> negativeBalanceWorkers() {

        NegativeBalanceDTO dto = new NegativeBalanceDTO();
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/most_expensive_task/{interval}")
    public ResponseEntity<MostExpensiveTaskDTO> mostExpensiveTask(@PathVariable("interval") MostExpensiveTaskInterval interval) {
        MostExpensiveTaskDTO dto = new MostExpensiveTaskDTO();
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

}