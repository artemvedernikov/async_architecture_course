package ru.avedernikov.asyncarchitecture.accountingbilling.repository;

import org.springframework.stereotype.Repository;
import ru.avedernikov.asyncarchitecture.accountingbilling.model.Account;

@Repository
public interface AccountBillingRepository extends JpaRepository<Account, UUID> {


}
