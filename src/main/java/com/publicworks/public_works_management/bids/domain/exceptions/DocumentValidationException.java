package com.publicworks.public_works_management.bids.domain.exceptions;

public class DocumentValidationException extends RuntimeException {
  public DocumentValidationException(String message) {
    super(message);
  }
}
