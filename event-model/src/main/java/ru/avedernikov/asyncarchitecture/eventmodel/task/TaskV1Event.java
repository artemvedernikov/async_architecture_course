package ru.avedernikov.asyncarchitecture.eventmodel.task;

import java.util.UUID;

public class TaskV1Event {

    public enum TaskEventType {
        TASK_CREATED,
        TASK_UPDATED
    }

    private final TaskEventType eventType;

    private final String taskPublicId;

    private final String title;

    public TaskV1Event(TaskEventType eventType, String taskPublicId, String title) {
        this.eventType = eventType;
        this.taskPublicId = taskPublicId;
        this.title = title;
    }

    public TaskEventType getEventType() {
        return eventType;
    }

    public String getTaskPublicId() {
        return taskPublicId;
    }

    public String getTitle() {
        return title;
    }
}
