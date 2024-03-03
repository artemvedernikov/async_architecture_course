package ru.avedernikov.asyncarchitecture.eventmodel.account;

import java.util.UUID;

public class AccountEvent {

    public enum AccountEventType {
        ACCOUNT_CREATED,
        ACCOUNT_UPDATED,
        ACCOUNT_DELETED
    }

    private final UUID accountId;

    private final AccountEventType eventType;

    public UUID getAccountId() {
        return accountId;
    }

    public AccountEventType getEventType() {
        return eventType;
    }
}
