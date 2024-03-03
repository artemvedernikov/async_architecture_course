package ru.avedernikov.asyncarchitecture.tasktracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import ru.avedernikov.asyncarchitecture.eventmodel.task.TaskEvent;
import ru.avedernikov.asyncarchitecture.tasktracker.dto.TaskDTO;
import ru.avedernikov.asyncarchitecture.tasktracker.model.Task;
import ru.avedernikov.asyncarchitecture.tasktracker.repository.TaskRepository;
import ru.avedernikov.asyncarchitecture.tasktracker.utils.TaskEventConverter;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/v1/tasks")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private KafkaTemplate<UUID, TaskEvent> taskEventTemplate;

    @PostMapping("/")
    ResponseEntity<Task> createTask(@RequestBody TaskDTO taskDTO) {
        Task task = null;
        TaskEvent event = TaskEventConverter.taskToTaskEvent(TaskEvent.TaskEventType.TASK_CREATED, task);
        taskEventTemplate.send()
    }


    @PutMapping("/{id}")
    ResponseEntity<Task> updateTask(@PathVariable("id") UUID id, @RequestBody TaskDTO taskDTO) {
        Optional<Task> taskData = taskRepository.findById(id);
        if (taskData.isPresent()) {
            Task task = null;
            TaskEvent event = TaskEventConverter.taskToTaskEvent(TaskEvent.TaskEventType.TASK_UPDATED, task);
            taskEventTemplate.send()
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // todo: should we do it based on role?
    @GetMapping()
    ResponseEntity<List<Task>> getTasks() {

    }

    @PostMapping("actions/reassign_tasks")
    HttpEntity reassignTasks() {
        List<Task> notDoneTasks = taskRepository.findNotDoneTasks();
        notDoneTasks.stream().map
    }

}
