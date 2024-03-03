package ru.avedernikov.asyncarchitecture.tasktracker.utils;

import ru.avedernikov.asyncarchitecture.eventmodel.task.TaskEvent;
import ru.avedernikov.asyncarchitecture.tasktracker.model.Task;

public class TaskEventConverter {
    public static TaskEvent taskToTaskEvent(TaskEvent.TaskEventType eventType, Task task) {
        return new TaskEvent(eventType, task.getId());
    }
}
