package com.publicworks.public_works_management.bids.application.usecase;

import com.publicworks.public_works_management.bids.application.command.CreateBidCommand;
import com.publicworks.public_works_management.bids.application.dto.BidResponse;
import com.publicworks.public_works_management.bids.application.mappers.BidMapper;
import com.publicworks.public_works_management.bids.application.ports.input.BidCreatorUseCase;
import com.publicworks.public_works_management.bids.application.services.BidServiceImpl;
import com.publicworks.public_works_management.bids.domain.entities.Bid;
import com.publicworks.public_works_management.bids.domain.exceptions.BidValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BidCreatorUseCaseImpl implements BidCreatorUseCase {
    private final BidServiceImpl bidService;
    private final BidMapper bidMapper;

    @Override
    public BidResponse execute(CreateBidCommand command) throws BidValidationException {
        Bid newBid = bidMapper.commandToEntity(command);

        Bid bidCreated =  bidService.createBid(newBid);

        return bidMapper.toResponse(bidCreated);
    }
}
