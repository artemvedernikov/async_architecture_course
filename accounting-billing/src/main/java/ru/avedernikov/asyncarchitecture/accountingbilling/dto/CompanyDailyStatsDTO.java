package ru.avedernikov.asyncarchitecture.accountingbilling.dto;

import java.time.LocalDate;

public class CompanyDailyStatsDTO {

    private final LocalDate date;

    private final double earnedAmount;

    public CompanyDailyStatsDTO(LocalDate date, double earnedAmount) {
        this.date = date;
        this.earnedAmount = earnedAmount;
    }
}
