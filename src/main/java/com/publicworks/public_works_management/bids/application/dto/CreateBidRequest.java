package com.publicworks.public_works_management.bids.application.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CreateBidRequest(
        @NotBlank String code,
        @NotBlank String name,
        String description,
        @Future LocalDate openingDate,
        @Future LocalDate closingDate,
        @Positive BigDecimal budget
) {}
