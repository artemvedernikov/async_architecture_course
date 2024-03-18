package ru.avedernikov.asyncarchitecture.accountingbilling.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "billing_cycles")
public class BillingCycle {

    private Long id;

    private Account account;

    private BillingCycleState state;

    private LocalDate start;

    private LocalDate end;
}
