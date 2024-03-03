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

    public AccountEventType getEventType() {
        return eventType;
    }

    public UUID getAccountId() {
        return accountId;
    }

    public AccountEvent(AccountEventType eventType, UUID accountId) {
        this.eventType = eventType;
        this.accountId = accountId;
    }
}
