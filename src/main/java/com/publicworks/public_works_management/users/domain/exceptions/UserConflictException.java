package com.publicworks.public_works_management.users.domain.exceptions;

public class UserConflictException extends RuntimeException {
    public UserConflictException(String message) {
        super(message);
    }
}
