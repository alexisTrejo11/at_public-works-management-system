package com.publicworks.public_works_management.bids.domain.valueobjects;

public enum BidStatus {
    DRAFT, PUBLISHED, UNDER_REVIEW, AWARDED, REJECTED;

    public boolean canTransitionTo(BidStatus newStatus) {
        // To Be Implement
        return true;
    }}