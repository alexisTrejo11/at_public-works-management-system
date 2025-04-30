package com.publicworks.public_works_management.users.application.dto;

import com.publicworks.public_works_management.users.domain.entity.User;

public record UserSummaryResponse(
        String id,
        String email,
        String phoneNumber,
        String role,
        String status
) {
    public static UserSummaryResponse fromDomain(User user) {
        return new UserSummaryResponse(
                user.getId().value().toString(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getRole().name(),
                user.getStatus().name()
        );
    }
}