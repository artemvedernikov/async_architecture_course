package ru.avedernikov.asyncarchitecture.eventmodel.task;

public class TaskCompletedV1Event {

    private final String taskPublicId;

    public TaskCompletedV1Event(String taskPublicId) {
        this.taskPublicId = taskPublicId;
    }

    public String getTaskPublicId() {
        return taskPublicId;
    }
}
