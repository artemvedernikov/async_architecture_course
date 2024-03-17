package ru.avedernikov.asyncarchitecture.analytics.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.avedernikov.asyncarchitecture.analytics.model.Task;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    public Optional<Task> findMostExpensiveTaskInInterval(LocalDate intervalStart, LocalDate intervalEnd);

    public Optional<Task> findByPublicId(String publicId);

}
