package ru.avedernikov.asyncarchitecture.accountingbilling.repository;

import org.springframework.stereotype.Repository;
import ru.avedernikov.asyncarchitecture.accountingbilling.model.AccountBilling;

@Repository
public interface AccountBillingRepository extends JpaRepository<AccountBilling, UUID> {


}
