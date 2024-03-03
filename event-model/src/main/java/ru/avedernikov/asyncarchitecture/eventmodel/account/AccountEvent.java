package ru.avedernikov.asyncarchitecture.eventmodel.account;

import java.util.Map;

public class AccountEvent {

    private String userId;
    private String type;

    private Map<String, String> details;

    public String getUserId() {
        return userId;
    }

    public String getType() {
        return type;
    }

    public Map<String, String> getDetails() {
        return details;
    }
}
