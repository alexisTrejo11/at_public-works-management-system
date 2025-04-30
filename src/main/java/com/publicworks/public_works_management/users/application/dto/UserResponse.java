package com.publicworks.public_works_management.users.application.dto;

import com.publicworks.public_works_management.users.domain.entity.User;

public record UserResponse(
        String id,
        String email,
        String phoneNumber,
        String status,
        String role,
        String createdAt,
        String updatedAt
) {
    public static UserResponse fromDomain(User user) {
        return new UserResponse(
                user.getId().value().toString(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getStatus().name(),
                user.getRole().name(),
                user.getCreatedAt().toString(),
                user.getUpdatedAt().toString()
        );
    }
}
