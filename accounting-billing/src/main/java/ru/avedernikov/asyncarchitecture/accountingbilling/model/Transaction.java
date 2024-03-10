package ru.avedernikov.asyncarchitecture.accountingbilling.model;

import jakarta.persistence.*;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="assignee_id")
    private Account assignee;

    @ManyToOne
    @JoinColumn(name="task_id")
    private Task task;


    private Double amount;



}
