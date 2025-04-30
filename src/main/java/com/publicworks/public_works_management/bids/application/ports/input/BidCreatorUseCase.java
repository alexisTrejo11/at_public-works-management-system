package com.publicworks.public_works_management.bids.application.ports.input;

import com.publicworks.public_works_management.bids.application.command.CreateBidCommand;
import com.publicworks.public_works_management.bids.application.dto.BidResponse;
import com.publicworks.public_works_management.bids.domain.exceptions.BidValidationException;

public interface BidCreatorUseCase {
    BidResponse execute(CreateBidCommand command) throws BidValidationException;
}

