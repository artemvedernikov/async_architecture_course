package ru.avedernikov.asyncarchitecture.tasktracker.utils;

import ru.avedernikov.asyncarchitecture.eventmodel.task.TaskEventV1;
import ru.avedernikov.asyncarchitecture.tasktracker.model.Task;

public class TaskEventConverter {
    public static TaskEventV1 taskToTaskEvent(TaskEventV1.TaskEventType eventType, Task task) {
        return new TaskEventV1(eventType, task.getId(), task.getTitle());
    }
}
