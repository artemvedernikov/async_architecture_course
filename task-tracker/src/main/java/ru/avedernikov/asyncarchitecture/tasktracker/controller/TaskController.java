package ru.avedernikov.asyncarchitecture.tasktracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import ru.avedernikov.asyncarchitecture.eventmodel.task.*;
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
    private KafkaTemplate<String, TaskV1Event> taskV1EventProducer;
    @Autowired
    private KafkaTemplate<String, TaskV2Event> taskV2EventProducer;
    @Autowired
    private KafkaTemplate<String, TaskCreatedV1Event> taskCreatedV1EventProducer;
    @Autowired
    private KafkaTemplate<String, TaskAssignedV1Event> taskAssignedV1EventProducer;
    @Autowired
    private KafkaTemplate<String, TaskCompletedV1Event> taskCompletedV1EventProducer;

    @Value(value = "${spring.kafka.task-streaming-v1-topic-name}")
    private String taskStreamingV1TopicName;

    @Value(value = "${spring.kafka.task-streaming-v2-topic-name}")
    private String taskStreamingV2TopicName;

    @Value(value = "${spring.kafka.task-created-v1-topic-name}")
    private String taskCreatedV1TopicName;

    @Value(value = "${spring.kafka.task-assigned-v1-topic-name}")
    private String taskAssignedV1TopicName;

    @Value(value = "${spring.kafka.task-completed-v1-topic-name}")
    private String taskCompletedV1TopicName;


    private final Random randomGenerator = new Random();

    @PostMapping("/")
    ResponseEntity<Task> createTask(@RequestBody TaskDTO taskDTO) {
        List<Account> workers = accountRepository.findByRole(AccountRole.worker);
        Optional<Account> worker = getRandomWorker(workers);
        if (worker.isPresent()) {
            Task task = new Task(
                taskDTO.getName(),
                false,
                worker.get()
            );
            taskRepository.save(task);

            TaskV1Event eventV1 = TaskEventConverter.taskToTaskEventV1(TaskV1Event.TaskEventType.TASK_UPDATED, task);
            taskV1EventProducer.send(taskStreamingV1TopicName, eventV1);

            TaskV2Event eventV2 = TaskEventConverter.taskToTaskEventV2(TaskV1Event.TaskEventType.TASK_UPDATED, task);
            taskV2EventProducer.send(taskStreamingV2TopicName, eventV2);

            TaskCreatedV1Event createdV1Event = new TaskCreatedV1Event(task.getPublicId().toString(), task.getAssignee().getPublicId().toString());
            taskCreatedV1EventProducer.send(taskCreatedV1TopicName, createdV1Event);

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
            TaskV1Event eventV1 = TaskEventConverter.taskToTaskEventV1(TaskV1Event.TaskEventType.TASK_UPDATED, task);
            taskV1EventProducer.send(taskStreamingV1TopicName, eventV1);

            TaskV2Event eventV2 = TaskEventConverter.taskToTaskEventV2(TaskV1Event.TaskEventType.TASK_UPDATED, task);
            taskV2EventProducer.send(taskStreamingV2TopicName, eventV2);

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
        List<Account> workers = accountRepository.findByRole(AccountRole.worker);

        List<Task> updatedTasks = notDoneTasks.stream().map(t -> {
            int index = randomGenerator.nextInt(workers.size());
            Account newAssignee = workers.get(index);
            t.setAssignee(newAssignee);
            return t;
        }).collect(Collectors.toList());

        taskRepository.saveAllAndFlush(updatedTasks);
        updatedTasks.forEach(t -> {
            TaskAssignedV1Event assignedV1Event = new TaskAssignedV1Event(t.getPublicId().toString(), t.getAssignee().getPublicId().toString());
            taskAssignedV1EventProducer.send(taskAssignedV1TopicName, assignedV1Event);
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

            TaskCompletedV1Event event = new TaskCompletedV1Event(task.getPublicId().toString());
            taskCompletedV1EventProducer.send(taskCompletedV1TopicName, event);

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
