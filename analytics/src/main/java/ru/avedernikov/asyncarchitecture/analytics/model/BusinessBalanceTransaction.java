package ru.avedernikov.asyncarchitecture.analytics.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "business_balance_transaction")
public class BusinessBalanceTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date eventDate;
    private double diff;


}
