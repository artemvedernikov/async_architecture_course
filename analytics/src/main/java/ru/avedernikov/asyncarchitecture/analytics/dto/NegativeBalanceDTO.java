package ru.avedernikov.asyncarchitecture.analytics.dto;

import java.time.LocalDate;

public class NegativeBalanceDTO {
    private LocalDate date;

    private Integer negativeBalance;

    public NegativeBalanceDTO(LocalDate date, Integer negativeBalance) {
        this.date = date;
        this.negativeBalance = negativeBalance;
    }
}
