package ru.avedernikov.asyncarchitecture.analytics.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "accounts_analytics")
public class Account {

    private final String publicId;

}
