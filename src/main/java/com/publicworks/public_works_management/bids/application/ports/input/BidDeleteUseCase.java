package com.publicworks.public_works_management.bids.application.ports.input;

import com.publicworks.public_works_management.bids.domain.exceptions.BidNotFoundException;
import com.publicworks.public_works_management.bids.domain.valueobjects.BidId;

public interface BidDeleteUseCase {
    void execute(BidId id) throws BidNotFoundException;
}

