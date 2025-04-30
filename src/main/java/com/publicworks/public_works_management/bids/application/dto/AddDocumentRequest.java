package com.publicworks.public_works_management.bids.application.dto;

import jakarta.validation.constraints.NotBlank;

public record AddDocumentRequest(
        @NotBlank String name,
        @NotBlank String type,
        @NotBlank String contentBase64
) {}
