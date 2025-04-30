package com.publicworks.public_works_management.users.application.dto;

import com.publicworks.public_works_management.users.domain.valueobjects.UserStatus;
import jakarta.validation.constraints.Size;

public record UpdateUserRequest(
        @Size(min = 2, max = 50) String firstName,
        @Size(min = 2, max = 50) String lastName,
        UserStatus status
        //Department department
) {}