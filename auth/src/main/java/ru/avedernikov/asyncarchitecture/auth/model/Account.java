package ru.avedernikov.asyncarchitecture.auth.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class Account {

    @Id
    private UUID id;

    private AccountRole role;

    private boolean active;
}
