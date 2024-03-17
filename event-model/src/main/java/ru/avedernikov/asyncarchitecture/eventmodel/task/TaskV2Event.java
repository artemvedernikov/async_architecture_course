package ru.avedernikov.asyncarchitecture.eventmodel.task;

public class TaskV2Event {

    private final String taskPublicId;
    private final String title;
    private final String jiraId;

    public TaskV2Event(String taskPublicId, String title, String jiraId) {
        this.taskPublicId = taskPublicId;
        this.title = title;
        this.jiraId = jiraId;
    }

    public String getTaskPublicId() {
        return taskPublicId;
    }

    public String getTitle() {
        return title;
    }

    public String getJiraId() {
        return jiraId;
    }
}
