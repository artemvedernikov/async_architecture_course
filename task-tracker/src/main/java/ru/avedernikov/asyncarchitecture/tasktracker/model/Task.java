package ru.avedernikov.asyncarchitecture.tasktracker.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import java.util.UUID;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    private String name;

    private boolean done;

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean getDone() {
        return done;
    }
}
