package com.publicworks.public_works_management.contracts.domain.valueObjects;


public record ContractId(String value) {
    public static ContractId from(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Contract ID cannot be empty");
        }
        return new ContractId(value);
    }
}
