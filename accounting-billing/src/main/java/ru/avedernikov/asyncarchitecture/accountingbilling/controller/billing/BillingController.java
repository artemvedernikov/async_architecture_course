package ru.avedernikov.asyncarchitecture.accountingbilling.controller.billing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.avedernikov.asyncarchitecture.accountingbilling.repository.AccountBillingRepository;
import ru.avedernikov.asyncarchitecture.accountingbilling.repository.TaskBillingRepository;

@RestController
@RequestMapping("/v1/billing")
public class BillingController {

    @Autowired
    private AccountBillingRepository accountBillingRepository;

    @Autowired
    private TaskBillingRepository taskBillingRepository;


}
