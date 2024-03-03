package ru.avedernikov.asyncarchitecture.eventmodel.task;

import java.util.UUID;

public class TaskEvent {

    public enum TaskEventType {
        TASK_CREATED,
        TASK_UPDATED
    }

    private final UUID taskId;

    private final TaskEventType eventType;


    public UUID getTaskId() {
        return taskId;
    }

    public TaskEventType getEventType() {
        return eventType;
    }
}
