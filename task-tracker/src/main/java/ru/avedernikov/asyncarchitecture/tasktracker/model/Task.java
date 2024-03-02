package ru.avedernikov.asyncarchitecture.tasktracker.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class Task {

    @Id
    private UUID id;
}
