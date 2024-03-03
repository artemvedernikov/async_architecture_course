package ru.avedernikov.asyncarchitecture.tasktracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.avedernikov.asyncarchitecture.tasktracker.model.Account;
import ru.avedernikov.asyncarchitecture.tasktracker.model.AccountRole;

import java.util.List;
import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {
    public List<Account> findByRole(AccountRole accountRole);

    @Query(value = "insert into accounts(id, email, role) values (:id, :email, :role) on conflict(id) do update set accounts.email = :email, accounts.role = :role", nativeQuery = true)
    public void upsert(UUID id, String email, AccountRole role);

}
