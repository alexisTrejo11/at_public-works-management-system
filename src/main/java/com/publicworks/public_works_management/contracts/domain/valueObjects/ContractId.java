package com.publicworks.public_works_management.contracts.domain.valueObjects;


public record ContractId(String value) {
    public static ContractId from(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Contract ID cannot be empty");
        }
        return new ContractId(value);
    }

    public Long toLong() {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Contract ID is empty");
        }

        try {
            return Long.parseLong(this.value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Contract Id is not a number or can't be parsed to numerical value");
        }
    }
}
