package ru.avedernikov.asyncarchitecture.tasktracker.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @GeneratedValue(strategy = GenerationType.UUID)
    private String publicId;

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

    public Long getId() {
        return id;
    }

    public String getPublicId() {
        return publicId;
    }

    public String getTitle() {
        return title;
    }

    public boolean getDone() {
        return done;
    }

    public Account getAssignee() {
        return assignee;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public void setAssignee(Account account) {
        assignee = account;
    }
}
