package ru.avedernikov.asyncarchitecture.analytics.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.avedernikov.asyncarchitecture.analytics.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}
