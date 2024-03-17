package ru.avedernikov.asyncarchitecture.analytics.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "tasks_analytics")
public class Task {


    private String publicId;

    private Double cost;

    private Double reward;

    public String getPublicId() {
        return publicId;
    }

    public Double getCost() {
        return cost;
    }

    public Double getReward() {
        return reward;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public void setReward(Double reward) {
        this.reward = reward;
    }
}
