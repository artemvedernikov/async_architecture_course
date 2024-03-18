package ru.avedernikov.asyncarchitecture.accountingbilling.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.avedernikov.asyncarchitecture.accountingbilling.model.Account;
import ru.avedernikov.asyncarchitecture.accountingbilling.model.BillingCycle;

import java.time.LocalDate;

public interface BillingCycleRepository extends JpaRepository<BillingCycle, Long> {

    public BillingCycle getOrCreateOpenBillingCycle(Account account, LocalDate localDate);
}
