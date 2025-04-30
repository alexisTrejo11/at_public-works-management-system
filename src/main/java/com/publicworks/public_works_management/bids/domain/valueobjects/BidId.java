package com.publicworks.public_works_management.bids.domain.valueobjects;

import java.util.Objects;
import java.util.UUID;

public record BidId(String value) {
    public BidId(String value) {
        this.value = value;
        this.validateUUIDFormat();
    }


    public UUID toUUID() {
        return UUID.fromString(this.value);
    }

    public void validateUUIDFormat() {
        if (Objects.equals(this.value, "") || this.value == null) {
            throw new IllegalArgumentException("Bid Id is empty");
        }

        try {
            UUID.fromString(this.value);
        }
        catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid UUID format");
        }
    }

    public static BidId generate() {
        return new BidId(UUID.randomUUID().toString());
    }
}