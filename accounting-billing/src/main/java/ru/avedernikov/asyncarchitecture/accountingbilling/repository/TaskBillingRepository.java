package ru.avedernikov.asyncarchitecture.accountingbilling.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.avedernikov.asyncarchitecture.accountingbilling.model.TaskBilling;

import java.util.UUID;

@Repository
public interface TaskBillingRepository extends JpaRepository<TaskBilling, UUID> {
}
