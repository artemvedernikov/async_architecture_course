package ru.avedernikov.asyncarchitecture.accountingbilling.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name="assignee_id")
    private Account assignee;

    @ManyToOne
    @JoinColumn(name="task_id")
    private Task task;


    private Double amount;

    private Date date;

    public Double getAmount() {
        return amount;
    }

    public Date getDate() {
        return date;
    }
}
