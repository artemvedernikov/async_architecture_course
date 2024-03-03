package ru.avedernikov.asyncarchitecture.tasktracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.avedernikov.asyncarchitecture.tasktracker.model.Account;
import ru.avedernikov.asyncarchitecture.tasktracker.model.AccountRole;
import ru.avedernikov.asyncarchitecture.tasktracker.model.Task;

import java.util.List;
import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {
    public List<Account> findByAccountRole(AccountRole accountRole);

}
