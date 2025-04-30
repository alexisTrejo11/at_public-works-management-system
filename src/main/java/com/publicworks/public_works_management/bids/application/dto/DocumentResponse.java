package com.publicworks.public_works_management.bids.application.dto;

import com.publicworks.public_works_management.bids.domain.valueobjects.BidDocument;

public record DocumentResponse(
        String id,
        String name,
        String type
) {
    public static DocumentResponse fromDomain(BidDocument doc) {
        return new DocumentResponse(
                doc.getDocumentId(),
                doc.getName(),
                doc.getType()
        );
    }
}