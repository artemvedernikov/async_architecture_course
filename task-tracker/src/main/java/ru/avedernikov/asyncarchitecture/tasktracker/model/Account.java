package ru.avedernikov.asyncarchitecture.tasktracker.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private UUID publicId;

    private String email;

    @Enumerated(EnumType.STRING)
    private AccountRole role;

    private boolean active;

    public Long getId() {
        return id;
    }

    public UUID getPublicId() {
        return publicId;
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
