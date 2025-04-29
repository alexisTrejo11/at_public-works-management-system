package com.publicworks.public_works_management.contracts.domain.exceptions;

public class ContractNotFoundException extends RuntimeException {
    public ContractNotFoundException(String param, Object paramValue) {
        super(generateMessage(param, paramValue));
    }

    private static String generateMessage(String param, Object paramValue) {
        if (param == null || param.trim().isEmpty() || paramValue == null) {
            return "Contract Not Found";
        } else {
            return String.format("Contract with %s '%s' not found", param, paramValue);
        }
    }
}