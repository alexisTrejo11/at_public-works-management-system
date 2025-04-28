package com.publicworks.public_works_management.contracts.domain.exceptions;

public class IllegalStatusTransitionException extends ContractException {
    public IllegalStatusTransitionException(String message) {
        super(message);
    }
}