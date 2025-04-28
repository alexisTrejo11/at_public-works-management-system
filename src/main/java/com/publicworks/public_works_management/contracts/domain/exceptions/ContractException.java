package com.publicworks.public_works_management.contracts.domain.exceptions;

public abstract class ContractException extends RuntimeException {
    public ContractException(String message) {
        super(message);
    }
}