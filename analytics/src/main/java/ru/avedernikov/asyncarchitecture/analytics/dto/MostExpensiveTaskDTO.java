package ru.avedernikov.asyncarchitecture.analytics.dto;

import java.time.LocalDate;
import java.util.UUID;

public class MostExpensiveTaskDTO {
    private final String taskPublicId;
    private final Double cost;
    private final LocalDate requestDate;
    private final MostExpensiveTaskInterval interval;

    public MostExpensiveTaskDTO(String taskPublicId, Double cost, LocalDate requestDate, MostExpensiveTaskInterval interval) {
        this.taskPublicId = taskPublicId;
        this.cost = cost;
        this.requestDate = requestDate;
        this.interval = interval;
    }
}
