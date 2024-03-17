package ru.avedernikov.asyncarchitecture.tasktracker.utils;

import ru.avedernikov.asyncarchitecture.eventmodel.task.TaskV1Event;
import ru.avedernikov.asyncarchitecture.eventmodel.task.TaskV2Event;
import ru.avedernikov.asyncarchitecture.tasktracker.model.Task;

public class TaskEventConverter {
    public static TaskV1Event taskToTaskEventV1(TaskV1Event.TaskEventType eventType, Task task) {
        return new TaskV1Event(eventType, task.getId(), task.getTitle());
    }

    public static TaskV2Event taskToTaskEventV2(TaskV1Event.TaskEventType eventType, Task task) {
        return new TaskV2Event(eventType, task.getId(), task.getTitle());
    }
}
