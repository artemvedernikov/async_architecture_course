package ru.avedernikov.asyncarchitecture.analytics.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "tasks_analytics")
public class Task {


    private final String publicId;

    private Double cost;

    public String getPublicId() {
        return publicId;
    }

    public Double getCost() {
        return cost;
    }
}
