package ru.avedernikov.asyncarchitecture.accountingbilling.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "tasks_billing")
public class Task {
    private UUID publicId;

    private Double price;

    @ManyToOne
    @JoinColumn(name="assignee_id")
    private Account assignee;

}
