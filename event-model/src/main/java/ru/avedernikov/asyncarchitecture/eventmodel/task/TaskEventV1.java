package ru.avedernikov.asyncarchitecture.eventmodel.task;

import java.util.UUID;

public class TaskEventV1 {

    public enum TaskEventType {
        TASK_CREATED,
        TASK_UPDATED
    }

    private final TaskEventType eventType;

    private final UUID taskId;

    private final String title;

    public TaskEventV1(TaskEventType eventType, UUID taskId, String title) {
        this.eventType = eventType;
        this.taskId = taskId;
        this.title = title;
    }

    public TaskEventType getEventType() {
        return eventType;
    }

    public UUID getTaskId() {
        return taskId;
    }

    public String getTitle() {
        return title;
    }
}
