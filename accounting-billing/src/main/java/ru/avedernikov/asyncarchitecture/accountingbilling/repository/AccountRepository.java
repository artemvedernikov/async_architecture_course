package ru.avedernikov.asyncarchitecture.accountingbilling.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.avedernikov.asyncarchitecture.accountingbilling.model.Account;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    public Optional<Account> findOneByPublicId(String publicId);


}
