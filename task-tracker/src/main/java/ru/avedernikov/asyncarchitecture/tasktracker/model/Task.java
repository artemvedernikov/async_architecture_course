package ru.avedernikov.asyncarchitecture.tasktracker.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    private String name;

    private boolean done;

    @ManyToOne
    @JoinColumn(name="assignee_id")
    private Account assignee;

    public Task(String name, boolean done, Account assignee) {
        this.name = name;
        this.done = done;
        this.assignee = assignee;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean getDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public void setAssignee(Account account) {
        assignee = account;
    }
}
