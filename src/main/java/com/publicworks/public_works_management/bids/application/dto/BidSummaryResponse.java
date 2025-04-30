package com.publicworks.public_works_management.bids.application.dto;

import java.time.LocalDate;
import java.math.BigDecimal;

public record BidSummaryResponse(
        String id,
        String code,
        String name,
        LocalDate openingDate,
        LocalDate closingDate,
        BigDecimal budget,
        String status
) {}
