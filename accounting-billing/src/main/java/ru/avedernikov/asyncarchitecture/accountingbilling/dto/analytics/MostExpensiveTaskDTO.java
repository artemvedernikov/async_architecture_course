package ru.avedernikov.asyncarchitecture.accountingbilling.dto.analytics;

import java.time.LocalDate;
import java.util.UUID;

public class MostExpensiveTaskDTO {
    private UUID taskPublicId;
    private Double taskPrice;
    private LocalDate requestDate;
    private MostExpensiveTaskInterval interval;

    public MostExpensiveTaskDTO(UUID taskPublicId, Double taskPrice, LocalDate requestDate, MostExpensiveTaskInterval interval) {
        this.taskPublicId = taskPublicId;
        this.taskPrice = taskPrice;
        this.requestDate = requestDate;
        this.interval = interval;
    }
}
