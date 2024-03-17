package ru.avedernikov.asyncarchitecture.eventmodel.task;

public class TaskCreatedV1Event {

    private final String taskPublicId;
    private final String assigneePublicId;

    public TaskCreatedV1Event(String taskPublicId, String assigneePublicId) {
        this.taskPublicId = taskPublicId;
        this.assigneePublicId = assigneePublicId;
    }

    public String getTaskPublicId() {
        return taskPublicId;
    }

    public String getAssigneePublicId() {
        return assigneePublicId;
    }
}
