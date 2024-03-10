package ru.avedernikov.asyncarchitecture.tasktracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.avedernikov.asyncarchitecture.tasktracker.model.Task;

import java.util.List;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {

    @Query(value = "select * from tasks where done = false", nativeQuery = true)
    public List<Task> findNotDoneTasks();
}
