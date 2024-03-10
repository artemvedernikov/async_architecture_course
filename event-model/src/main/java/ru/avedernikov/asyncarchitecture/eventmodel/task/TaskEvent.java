package ru.avedernikov.asyncarchitecture.eventmodel.task;

import java.util.UUID;

public class TaskEvent {

    public enum TaskEventType {
        TASK_CREATED,
        TASK_UPDATED
    }

    private final TaskEventType eventType;

    private final UUID taskId;

    public TaskEvent(TaskEventType eventType, UUID taskId) {
        this.eventType = eventType;
        this.taskId = taskId;
    }

    public TaskEventType getEventType() {
        return eventType;
    }

    public UUID getTaskId() {
        return taskId;
    }
}
