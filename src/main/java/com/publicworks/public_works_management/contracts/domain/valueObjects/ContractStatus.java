package com.publicworks.public_works_management.contracts.domain.valueObjects;

public enum ContractStatus {
    DRAFT, PENDING_APPROVAL, ACTIVE, SUSPENDED, TERMINATED, COMPLETED;

    public boolean canTransitionTo(ContractStatus newStatus) {
        return switch (this) {
            case DRAFT -> newStatus == PENDING_APPROVAL;
            case PENDING_APPROVAL -> newStatus == ACTIVE || newStatus == DRAFT;
            case ACTIVE -> newStatus == SUSPENDED || newStatus == TERMINATED || newStatus == COMPLETED;
            case SUSPENDED -> newStatus == ACTIVE || newStatus == TERMINATED;
            default -> false;
        };
    }
}
