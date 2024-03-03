package ru.avedernikov.asyncarchitecture.tasktracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import ru.avedernikov.asyncarchitecture.eventmodel.task.TaskEvent;
import ru.avedernikov.asyncarchitecture.tasktracker.config.TaskEventProducerConfig;
import ru.avedernikov.asyncarchitecture.tasktracker.dto.TaskDTO;
import ru.avedernikov.asyncarchitecture.tasktracker.model.Account;
import ru.avedernikov.asyncarchitecture.tasktracker.model.AccountRole;
import ru.avedernikov.asyncarchitecture.tasktracker.model.Task;
import ru.avedernikov.asyncarchitecture.tasktracker.repository.AccountRepository;
import ru.avedernikov.asyncarchitecture.tasktracker.repository.TaskRepository;
import ru.avedernikov.asyncarchitecture.tasktracker.utils.TaskEventConverter;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/tasks")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private KafkaTemplate<UUID, TaskEvent> taskEventTemplate;

    private final Random randomGenerator = new Random();

    @PostMapping("/")
    ResponseEntity<Task> createTask(@RequestBody TaskDTO taskDTO) {
        List<Account> workers = accountRepository.findByAccountRole(AccountRole.WORKER);
        Optional<Account> worker = getRandomWorker(workers);
        if (worker.isPresent()) {
            Task task = new Task(
                taskDTO.getName(),
                false,
                worker.get()
            );
            taskRepository.save(task);
            TaskEvent event = TaskEventConverter.taskToTaskEvent(TaskEvent.TaskEventType.TASK_CREATED, task);
            taskEventTemplate.send("task-events", event);
            return new ResponseEntity<>(task, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/{id}")
    ResponseEntity<Task> updateTask(@PathVariable("id") UUID id, @RequestBody TaskDTO taskDTO) {
        Optional<Task> taskData = taskRepository.findById(id);
        if (taskData.isPresent()) {
            Task task = null;
            TaskEvent event = TaskEventConverter.taskToTaskEvent(TaskEvent.TaskEventType.TASK_UPDATED, task);
            taskEventTemplate.send("task-events", event);
            return new ResponseEntity<>(task, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // todo: should we do it based on role?
    @GetMapping("/")
    ResponseEntity<List<Task>> getTasks() {
        List<Task> tasks = taskRepository.findAll();
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<Task> getTaskById(@PathVariable("id") UUID id) {
        Optional<Task> taskData = taskRepository.findById(id);
        if (taskData.isPresent()) {
            return new ResponseEntity<>(taskData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("actions/reassign_tasks")
    ResponseEntity<?> reassignTasks(Principal principal) {
        List<Task> notDoneTasks = taskRepository.findNotDoneTasks();
        List<Account> workers = accountRepository.findByAccountRole(AccountRole.WORKER);

        List<Task> updatedTasks = notDoneTasks.stream().map(t -> {
            int index = randomGenerator.nextInt(workers.size());
            Account newAssignee = workers.get(index);
            t.setAssignee(newAssignee);
            return t;
        }).collect(Collectors.toList());

        taskRepository.saveAllAndFlush(updatedTasks);
        updatedTasks.forEach(t -> {
            TaskEvent event = TaskEventConverter.taskToTaskEvent(TaskEvent.TaskEventType.TASK_UPDATED, t);
            taskEventTemplate.send("task-events", event);
        });

        return ResponseEntity.ok().build();
    }

    @PostMapping("actions/complete/{id}")
    ResponseEntity completeTask(@PathVariable("id") UUID id) {
        Optional<Task> taskOpt = taskRepository.findById(id);
        if (taskOpt.isPresent()) {
            Task task = taskOpt.get();
            task.setDone(true);
            taskRepository.save(task);
            // todo: send BE
            return ResponseEntity.ok().build();
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    private Optional<Account> getRandomWorker(List<Account> workers) {
        if (!workers.isEmpty()) {
            int index = randomGenerator.nextInt(workers.size());
            Account randomWorker = workers.get(index);
            return Optional.of(randomWorker);
        } else {
            return Optional.empty();
        }
    }
}
