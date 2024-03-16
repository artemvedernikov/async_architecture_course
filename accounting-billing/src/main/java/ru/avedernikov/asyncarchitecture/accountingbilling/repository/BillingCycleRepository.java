package ru.avedernikov.asyncarchitecture.accountingbilling.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.avedernikov.asyncarchitecture.accountingbilling.model.BillingCycle;

public interface BillingCycleRepository extends JpaRepository<BillingCycle, Long> {
}
