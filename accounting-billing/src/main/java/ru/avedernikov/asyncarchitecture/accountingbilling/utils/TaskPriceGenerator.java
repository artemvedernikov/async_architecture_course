package ru.avedernikov.asyncarchitecture.accountingbilling.utils;

import java.util.concurrent.ThreadLocalRandom;

public class TaskPriceGenerator {

    private static final double COST_MIN = 10;
    private static final double COST_MAX = 20;

    private static final double REWARD_MIN = 20;
    private static final double REWARD_MAX = 30;

    public static double generateTaskCost() {
        return ThreadLocalRandom.current().nextDouble(COST_MIN, COST_MAX);
    }

    public static double generateTaskReward() {
        return ThreadLocalRandom.current().nextDouble(REWARD_MIN, REWARD_MAX);

    }

}
