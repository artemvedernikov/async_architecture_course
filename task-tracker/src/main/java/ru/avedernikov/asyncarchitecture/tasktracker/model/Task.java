package ru.avedernikov.asyncarchitecture.tasktracker.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    private String title;

    private String jiraId;

    private boolean done;

    @ManyToOne
    @JoinColumn(name="assignee_id")
    private Account assignee;

    public Task(String title, boolean done, Account assignee) {
        this.title = title;
        this.done = done;
        this.assignee = assignee;
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
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
