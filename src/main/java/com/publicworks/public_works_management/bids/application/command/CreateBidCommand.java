package com.publicworks.public_works_management.bids.application.command;

import com.publicworks.public_works_management.bids.application.dto.CreateBidRequest;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CreateBidCommand(
        String code,
        String name,
        String description,
        LocalDate openingDate,
        LocalDate closingDate,
        BigDecimal budget
) {
    public static CreateBidCommand fromRequest(CreateBidRequest request) {
        return new CreateBidCommand(
                request.code(),
                request.name(),
                request.description(),
                request.openingDate(),
                request.closingDate(),
                request.budget()
        );
    }
}
