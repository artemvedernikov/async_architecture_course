package ru.avedernikov.asyncarchitecture.accountingbilling.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "tasks_billing")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String publicId;

    private double cost;

    private double reward;

    @ManyToOne
    @JoinColumn(name="assignee_id")
    private Account assignee;


    public double getCost() {
        return cost;
    }

    public double getReward() {
        return reward;
    }

    public Account getAssignee() {
        return assignee;
    }

    public void setAssignee(Account assignee) {
        this.assignee = assignee;
    }
}
