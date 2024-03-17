package ru.avedernikov.asyncarchitecture.accountingbilling.dto;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class AccountBillingStateDTO {

    private LocalDate date;

    private Double amount;

    private String accountPublicId;

    private List<TransactionDTO> transactions;

    public static class TransactionDTO {
        private Date date;
        private Double amount;

        public TransactionDTO(Date date, Double amount) {
            this.date = date;
            this.amount = amount;
        }
    }

    public AccountBillingStateDTO(LocalDate date, Double amount, String accountPublicId, List<TransactionDTO> transactions) {
        this.date = date;
        this.amount = amount;
        this.accountPublicId = accountPublicId;
        this.transactions = transactions;
    }
}
