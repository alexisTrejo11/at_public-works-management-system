package com.publicworks.public_works_management.contracts.domain;

import com.publicworks.public_works_management.contracts.domain.exceptions.*;
import com.publicworks.public_works_management.contracts.domain.valueObjects.ContractId;
import com.publicworks.public_works_management.contracts.domain.valueObjects.ContractStatus;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

@Getter
@Builder
@ToString
public class Contract {
    private final ContractId id;
    private final String contractNumber;
    private final String title;
    private final String description;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final BigDecimal amount;

    @Setter(AccessLevel.PRIVATE)
    private ContractStatus status;

    @Builder.Default
    private final List<ContractClause> clauses = new ArrayList<>();

    // Domain Logic
    public void addClause(ContractClause clause) {
        Objects.requireNonNull(clause, "Clause cannot be null");
        if (!status.equals(ContractStatus.DRAFT)) {
            throw new ContractModificationException(
                    "Cannot add clauses to contract in " + status + " status");
        }
        clauses.add(clause);
    }

    public void approve() {
        validateApproval();
        this.status = ContractStatus.PENDING_APPROVAL;
    }

    public void transitionStatus(ContractStatus newStatus) {
        if (!status.canTransitionTo(newStatus)) {
            throw new IllegalStatusTransitionException(
                    "Invalid status transition from " + status + " to " + newStatus);
        }
        this.status = newStatus;
    }

    private void validateApproval() {
        if (clauses.isEmpty()) {
            throw new ContractValidationException("Contract must have at least one clause");
        }
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new ContractValidationException("Contract amount must be positive");
        }
        if (endDate.isBefore(startDate)) {
            throw new ContractValidationException("End date cannot be before start date");
        }
    }

    public List<ContractClause> getClauses() {
        return Collections.unmodifiableList(clauses);
    }
}