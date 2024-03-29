package ru.avedernikov.asyncarchitecture.accountingbilling.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.avedernikov.asyncarchitecture.accountingbilling.model.Transaction;

import java.util.List;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> getByAssigneeId(UUID assigneeId);

}
