package ru.avedernikov.asyncarchitecture.accountingbilling.dto.analytics;

import java.time.LocalDate;

public class DailyEarningsDTO {
    private LocalDate date;

    private Double amount;

    public DailyEarningsDTO(LocalDate date, Double amount) {
        this.date = date;
        this.amount = amount;
    }
}
