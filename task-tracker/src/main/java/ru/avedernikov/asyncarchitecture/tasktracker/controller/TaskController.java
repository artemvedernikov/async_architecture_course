package ru.avedernikov.asyncarchitecture.tasktracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.avedernikov.asyncarchitecture.tasktracker.repository.TaskRepository;

@RestController
@RequestMapping("/v1/tasks")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;



}
