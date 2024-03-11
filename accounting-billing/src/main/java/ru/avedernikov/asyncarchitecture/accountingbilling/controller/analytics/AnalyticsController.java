package ru.avedernikov.asyncarchitecture.accountingbilling.controller.analytics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.avedernikov.asyncarchitecture.accountingbilling.dto.analytics.DailyEarningsDTO;
import ru.avedernikov.asyncarchitecture.accountingbilling.dto.analytics.MostExpensiveTaskDTO;
import ru.avedernikov.asyncarchitecture.accountingbilling.dto.analytics.MostExpensiveTaskInterval;
import ru.avedernikov.asyncarchitecture.accountingbilling.dto.analytics.NegativeBalanceDTO;
import ru.avedernikov.asyncarchitecture.accountingbilling.repository.AccountRepository;
import ru.avedernikov.asyncarchitecture.accountingbilling.repository.TaskRepository;

import java.time.LocalDate;

@RestController
@RequestMapping("/v1/analytics")
public class AnalyticsController {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TaskRepository taskBillingRepository;

    @GetMapping("/daily_earnings")
    public ResponseEntity<DailyEarningsDTO> dailyEarnings() {
        LocalDate today = LocalDate.now();
    }

    @GetMapping("/negative_balance_workers")
    public ResponseEntity<NegativeBalanceDTO> negativeBalanceWorkers() {

    }

    @GetMapping("/most_expensive_task/{interval}")
    public ResponseEntity<MostExpensiveTaskDTO> mostExpensiveTask(@PathVariable("interval") MostExpensiveTaskInterval interval) {

    }

}
