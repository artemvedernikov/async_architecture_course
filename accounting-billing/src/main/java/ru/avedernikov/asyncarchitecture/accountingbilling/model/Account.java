package ru.avedernikov.asyncarchitecture.accountingbilling.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "accounts_billing")
public class Account {


    private String publicId;

}
