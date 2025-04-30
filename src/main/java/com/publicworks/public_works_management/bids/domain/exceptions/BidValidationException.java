package com.publicworks.public_works_management.bids.domain.exceptions;

public class BidValidationException extends RuntimeException {
    public BidValidationException(String message) {
        super(message);
    }
}
