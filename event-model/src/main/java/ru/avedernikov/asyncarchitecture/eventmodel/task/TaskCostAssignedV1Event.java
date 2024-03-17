package ru.avedernikov.asyncarchitecture.eventmodel.task;

import java.util.UUID;

public class TaskCostAssignedV1Event {

    private final TaskCostAssignedV1EventMeta meta;
    private final TaskCostAssignedV1EventData data;

    public static class TaskCostAssignedV1EventMeta {
        private final String eventId;

        public TaskCostAssignedV1EventMeta() {
            this.eventId = UUID.randomUUID().toString();
        }
    }

    public static class TaskCostAssignedV1EventData {
        private final String taskPublicId;
        private final double cost;
        private final double reward;

        public TaskCostAssignedV1EventData(String taskPublicId, double cost, double reward) {
            this.taskPublicId = taskPublicId;
            this.cost = cost;
            this.reward = reward;
        }

        public String getTaskPublicId() {
            return taskPublicId;
        }

        public double getCost() {
            return cost;
        }

        public double getReward() {
            return reward;
        }
    }

    public TaskCostAssignedV1Event(TaskCostAssignedV1EventMeta meta, TaskCostAssignedV1EventData data) {
        this.meta = meta;
        this.data = data;
    }

    public TaskCostAssignedV1EventMeta getMeta() {
        return meta;
    }

    public TaskCostAssignedV1EventData getData() {
        return data;
    }
}
