package ru.avedernikov.asyncarchitecture.accountingbilling.dto.billing;

import java.time.LocalDate;

public class CompanyDailyStatsDTO {

    private LocalDate date;

    private Double earnedAmount;

    public CompanyDailyStatsDTO(LocalDate date, Double earnedAmount) {
        this.date = date;
        this.earnedAmount = earnedAmount;
    }
}
