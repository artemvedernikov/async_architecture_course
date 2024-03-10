package ru.avedernikov.asyncarchitecture.accountingbilling.dto.billing;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class AccountBillingStateDTO {

    private LocalDate date;

    private Double amount;

    private UUID accountPublicId;

    private List<TransactionDTO> transactions;

    public static class TransactionDTO {
        private Date date;
        private Double amount;
    }

    public AccountBillingStateDTO(LocalDate date, Double amount, UUID accountPublicId, List<TransactionDTO> transactions) {
        this.date = date;
        this.amount = amount;
        this.accountPublicId = accountPublicId;
        this.transactions = transactions;
    }
}
