package ru.avedernikov.asyncarchitecture.accountingbilling.dto.analytics;

import java.time.LocalDate;
import java.util.UUID;

public class MostExpensiveTaskDTO {
    private UUID taskPublicId;
    private Double taskPrice;
    private LocalDate date;
    private MostExpensiveTaskInterval interval;

    public MostExpensiveTaskDTO(UUID taskPublicId, Double taskPrice, LocalDate date, MostExpensiveTaskInterval interval) {
        this.taskPublicId = taskPublicId;
        this.taskPrice = taskPrice;
        this.date = date;
        this.interval = interval;
    }
}
