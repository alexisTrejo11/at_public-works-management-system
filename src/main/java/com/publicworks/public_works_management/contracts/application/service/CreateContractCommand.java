package com.publicworks.public_works_management.contracts.application.service;

import com.publicworks.public_works_management.contracts.infrastructure.ports.output.persistence.ContractClauseModel;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record CreateContractCommand(
        String title,
        String description,
        LocalDate startDate,
        LocalDate endDate,
        BigDecimal amount,
        List<ContractClauseModel> clauses
) {}
