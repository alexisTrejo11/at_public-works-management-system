package com.publicworks.public_works_management.users.application.command;

import com.publicworks.public_works_management.users.domain.valueobjects.UserId;
import com.publicworks.public_works_management.users.domain.valueobjects.UserStatus;

public record UpdateUserCommand(
        UserId userId,
        String firstName,
        String lastName,
        UserStatus status
        //Department department
) {}
