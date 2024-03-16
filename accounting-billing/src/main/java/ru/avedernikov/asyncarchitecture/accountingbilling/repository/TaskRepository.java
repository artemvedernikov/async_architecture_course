package ru.avedernikov.asyncarchitecture.accountingbilling.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.avedernikov.asyncarchitecture.accountingbilling.model.Task;

import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {
}
