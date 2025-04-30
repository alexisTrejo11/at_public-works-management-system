package com.publicworks.public_works_management.bids.application.command;

import com.publicworks.public_works_management.bids.application.dto.AddDocumentRequest;

import java.util.Base64;

public record AddDocumentCommand(
        String bidId,
        String name,
        String type,
        byte[] content
) {
    public static AddDocumentCommand fromRequest(
            String bidId,
            AddDocumentRequest request
    ) {
        return new AddDocumentCommand(
                bidId,
                request.name(),
                request.type(),
                Base64.getDecoder().decode(request.contentBase64())
        );
    }
}
