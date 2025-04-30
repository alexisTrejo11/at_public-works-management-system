package com.publicworks.public_works_management.users.application.command;

import com.publicworks.public_works_management.users.application.dto.CreateUserRequest;
import com.publicworks.public_works_management.users.domain.valueobjects.UserRole;

// CreateUserCommand.java
public record CreateUserCommand(
        String email,
        String phoneNumber,
        String password,
        UserRole role
        //Department department
) {
    public static CreateUserCommand fromRequest(CreateUserRequest request) {
        return new CreateUserCommand(
                request.email(),
                request.phoneNumber(),
                request.role()
        );
    }
}

