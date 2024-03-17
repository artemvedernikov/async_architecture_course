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
import ru.avedernikov.asyncarchitecture.analytics.model.Task;
import ru.avedernikov.asyncarchitecture.analytics.repository.AccountRepository;
import ru.avedernikov.asyncarchitecture.analytics.repository.BusinessBalanceTransactionRepository;
import ru.avedernikov.asyncarchitecture.analytics.repository.TaskRepository;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("/v1/analytics")
public class AnalyticsController {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TaskRepository taskBillingRepository;

    @Autowired
    private BusinessBalanceTransactionRepository businessBalanceTransactionRepository;

    // todo: add role check
    @GetMapping("/daily_earnings")
    public ResponseEntity<DailyEarningsDTO> dailyEarnings() {
        LocalDate today = LocalDate.now();

        DailyEarningsDTO dto = new DailyEarningsDTO();
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    // todo: add role check
    @GetMapping("/negative_balance_workers")
    public ResponseEntity<NegativeBalanceDTO> negativeBalanceWorkers() {

        NegativeBalanceDTO dto = new NegativeBalanceDTO();
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    // todo: add role check
    @GetMapping("/most_expensive_task/{interval}")
    public ResponseEntity<MostExpensiveTaskDTO> mostExpensiveTask(@PathVariable("interval") MostExpensiveTaskInterval interval) {
        LocalDate today = LocalDate.now();
        LocalDate start = switch (interval) {
            case DAY -> today.minusDays(1);
            case WEEK ->  today.minusDays(8);
            case MONTH -> today.minusDays(31);
        };
        Optional<Task> mostExpensiveTaskOpt = taskBillingRepository.findMostExpensiveTaskInInterval(start, today);
        if (mostExpensiveTaskOpt.isPresent()) {
            Task mostExpensiveTask = mostExpensiveTaskOpt.get();
            MostExpensiveTaskDTO dto = new MostExpensiveTaskDTO(
                 mostExpensiveTask.getPublicId(),
                 mostExpensiveTask.getCost(),
                 today,
                interval
            );
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

}