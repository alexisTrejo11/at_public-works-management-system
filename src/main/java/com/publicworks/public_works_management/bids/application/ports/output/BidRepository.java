package com.publicworks.public_works_management.bids.application.ports.output;

import com.publicworks.public_works_management.bids.domain.entities.Bid;
import com.publicworks.public_works_management.bids.domain.valueobjects.BidId;
import com.publicworks.public_works_management.bids.domain.valueobjects.BidStatus;

import java.util.List;
import java.util.Optional;

public interface BidRepository {
    Bid save(Bid bid);
    Optional<Bid> findById(BidId bidId);
    List<Bid> findByStatus(BidStatus status);
}