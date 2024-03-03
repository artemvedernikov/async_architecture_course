package ru.avedernikov.asyncarchitecture.eventmodel.account;

import java.util.UUID;

public class AccountEvent {

    public enum AccountEventType {
        ACCOUNT_CREATED,
        ACCOUNT_UPDATED,
        ACCOUNT_DELETED
    }

    private final AccountEventType eventType;

    private final UUID accountId;

    private final String email;

    private final String role;

    public AccountEventType getEventType() {
        return eventType;
    }

    public UUID getAccountId() {
        return accountId;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public AccountEvent(AccountEventType eventType, UUID accountId, String email, String role) {
        this.eventType = eventType;
        this.accountId = accountId;
        this.email = email;
        this.role = role;
    }
}
