package com.publicworks.public_works_management.users.domain.entity;

import com.publicworks.public_works_management.users.domain.exceptions.UserValidationException;
import com.publicworks.public_works_management.users.domain.valueobjects.UserId;
import com.publicworks.public_works_management.users.domain.valueobjects.UserRole;
import com.publicworks.public_works_management.users.domain.valueobjects.UserStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class User {
    private final UserId id;
    private final String email;
    private final String phoneNumber;
    private final String password;
    private final UserStatus status;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final UserRole role;

    public static User create(String email, String phoneNumber, UserRole role) {
        validateEmail(email);
        validatePhoneNumber(phoneNumber);

        return User.builder()
                .id(UserId.generate())
                .email(email.trim().toLowerCase())
                .phoneNumber(phoneNumber.trim())
                .status(UserStatus.ACTIVE)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .role(role)
                .build();
    }

    private static void validateEmail(String email) {
        if (!email.matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            throw new UserValidationException("Invalid email format");
        }
    }

    public void update(User user) {
        return;
    }

    public void validatePassword(String requestedPassword) {
        return;
    }

    private static void validatePhoneNumber(String phoneNumber) {
        if (phoneNumber.length() < 8 || phoneNumber.length() > 10) {
            throw new UserValidationException("Phone number must be between 8 and 10 characters");
        }

        if (!phoneNumber.trim().matches("\\d+")) {
            throw new UserValidationException("Phone number must contain only numbers");
        }

    }

    public void changePassword(String newPassword) {
        return;
    }
}