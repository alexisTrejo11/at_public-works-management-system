package com.publicworks.public_works_management.users.application.dto;

import com.publicworks.public_works_management.users.domain.valueobjects.UserRole;
import jakarta.validation.constraints.*;

public record CreateUserRequest(
        @NotBlank @Email String email,
        @NotBlank @Size(min = 2, max = 50) String firstName,
        @NotBlank @Size(min = 2, max = 50) String lastName,
        @NotBlank @Size(min = 2, max = 50) String phoneNumber,
        @NotBlank @Size(min = 2, max = 255) String password,
        @Pattern(regexp = "^\\d{8}[A-Z]$") String dni,
        @NotNull UserRole role
        //@NotNull Department department
) {}
