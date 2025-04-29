package com.publicworks.public_works_management.config.exceptions;

import com.publicworks.public_works_management.contracts.domain.exceptions.ContractClauseNotFoundException;
import com.publicworks.public_works_management.contracts.domain.exceptions.ContractNotFoundException;
import com.publicworks.public_works_management.shared.response.http.ResponseWrapper;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class CustomGlobalExceptionHandler {

    /* Validation Data Exceptions */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseWrapper<Void>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.toList());

        String errorMessage = String.join(", ", errors);
        StringBuilder validation = new StringBuilder("Validation Error");

        // Adjust the message based on the number of errors
        if (errors.size() > 1) {
            validation.append("s: "); // For multiple errors
        } else {
            validation.append(": "); // For a single error
        }

        // Complete the message with the actual error details
        validation.append(errorMessage);

        ResponseWrapper<Void> responseWrapper = new ResponseWrapper<>(
                false,
                null,
                validation.toString(),
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now()
        );

        return ResponseEntity.badRequest().body(responseWrapper);
    }

    /* Not Found Exceptions */
    @ExceptionHandler({ContractNotFoundException.class, ContractClauseNotFoundException.class})
    public ResponseEntity<ResponseWrapper<Void>> handleNotFoundExceptions(RuntimeException ex) {
        ResponseWrapper<Void> responseWrapper = new ResponseWrapper<>(
                false,
                null,
                ex.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(responseWrapper, HttpStatus.NOT_FOUND);
    }

    /* Generic Exception */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseWrapper<String>> handleException(Exception ex) {
        ResponseWrapper<String> response = new ResponseWrapper<>(
                false,
                null,
                "An unexpected error occurred: " + ex.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}