package com.publicworks.public_works_management.bids.application.ports.input;

import com.publicworks.public_works_management.bids.application.command.CreateBidCommand;
import com.publicworks.public_works_management.bids.application.dto.BidResponse;
import com.publicworks.public_works_management.bids.domain.exceptions.BidNotFoundException;
import com.publicworks.public_works_management.bids.domain.exceptions.BidValidationException;
import com.publicworks.public_works_management.bids.domain.valueobjects.BidId;

import java.text.Bidi;

public interface BidUpdateUseCase {
    BidResponse execute(CreateBidCommand command, BidId id) throws BidValidationException, BidNotFoundException;
}

