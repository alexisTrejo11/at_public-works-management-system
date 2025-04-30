package com.publicworks.public_works_management.bids.domain.valueobjects;

import lombok.Builder;
import lombok.Getter;
import lombok.Value;

@Value
@Builder
@Getter
public class BidDocument {
    String documentId;
    String name;
    String type;
    String storagePath;
}