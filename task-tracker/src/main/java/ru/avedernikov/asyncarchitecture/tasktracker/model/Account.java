package ru.avedernikov.asyncarchitecture.tasktracker.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    private String email;

    private AccountRole role;

    private boolean active;

    public UUID getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public AccountRole getRole() {
        return role;
    }

    public boolean isActive() {
        return active;
    }
}
