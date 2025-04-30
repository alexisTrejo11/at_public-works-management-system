package com.publicworks.public_works_management.bids.application.dto;

import com.publicworks.public_works_management.bids.domain.entities.Bid;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record BidResponse(
        String id,
        String code,
        String name,
        String description,
        LocalDate openingDate,
        LocalDate closingDate,
        BigDecimal budget,
        String status,
        List<DocumentResponse> documents
) {
    public static BidResponse fromDomain(Bid bid) {
        return new BidResponse(
                bid.getId().value(),
                bid.getCode(),
                bid.getName(),
                bid.getDescription(),
                bid.getOpeningDate(),
                bid.getClosingDate(),
                bid.getBudget(),
                bid.getStatus().name(),
                bid.getDocuments().stream()
                        .map(DocumentResponse::fromDomain)
                        .toList()
        );
    }
}

