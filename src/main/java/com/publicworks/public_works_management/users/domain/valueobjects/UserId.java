package com.publicworks.public_works_management.users.domain.valueobjects;

import lombok.Value;

import java.util.UUID;

public record UserId(UUID value) {
    public static UserId generate() {
        return new UserId(UUID.randomUUID());
    }

    public static UserId fromString(String value) {
        return new UserId(UUID.fromString(value));
    }
}
