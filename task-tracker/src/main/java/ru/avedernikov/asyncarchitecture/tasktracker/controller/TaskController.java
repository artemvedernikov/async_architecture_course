package ru.avedernikov.asyncarchitecture.tasktracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import ru.avedernikov.asyncarchitecture.eventmodel.task.TaskEvent;
import ru.avedernikov.asyncarchitecture.tasktracker.model.Task;
import ru.avedernikov.asyncarchitecture.tasktracker.repository.TaskRepository;
import ru.avedernikov.asyncarchitecture.tasktracker.utils.TaskEventConverter;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/tasks")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private KafkaTemplate<UUID, TaskEvent> taskEventTemplate;

    @PostMapping
    Task createTask() {
        Task task = null;
        TaskEvent event = TaskEventConverter.taskToTaskEvent(task);
        taskEventTemplate.send()
    }


    @PutMapping()
    Task updateTask() {

    }

    // todo: should we do it based on role?
    @GetMapping()
    List<Task> getTasks() {

    }

}
