package com.publicworks.public_works_management.contracts.domain;

import com.publicworks.public_works_management.contracts.domain.exceptions.*;
import com.publicworks.public_works_management.contracts.domain.valueObjects.ContractId;
import com.publicworks.public_works_management.contracts.domain.valueObjects.ContractStatus;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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

    public void validateFields() {
        this.validateStartDate();
        this.validateEndDate();
    }

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

    public Boolean is_active() {
        LocalDate now = LocalDate.now();
        return now.isBefore(this.endDate) &&
                this.status != ContractStatus.COMPLETED &&
                this.status != ContractStatus.TERMINATED;

    }

    public long getDaysRemaining() {
        LocalDate now = LocalDate.now();
        long daysRemaining = 0;

        if (this.endDate != null) {
            daysRemaining = Math.max(0, (int) ChronoUnit.DAYS.between(now, this.endDate));
        }

        return daysRemaining;
    }

    public void validateStartDate() {
        if (startDate == null || startDate.isBefore(LocalDate.now())) {
            throw new ContractValidationException("La fecha de inicio del contrato debe ser hoy o en el futuro.");
        }
    }

    public void validateEndDate() {
        if (endDate == null || endDate.isBefore(startDate)) {
            throw new ContractValidationException("La fecha de finalización del contrato debe ser posterior o igual a la fecha de inicio.");
        }
    }
}
