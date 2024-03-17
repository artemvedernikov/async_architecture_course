package ru.avedernikov.asyncarchitecture.eventmodel;

public class BusinessBalanceChangedV1Event {
    private final double balanceChange;

    public BusinessBalanceChangedV1Event(double balanceChange) {
        this.balanceChange = balanceChange;
    }

    public double getBalanceChange() {
        return balanceChange;
    }
}
