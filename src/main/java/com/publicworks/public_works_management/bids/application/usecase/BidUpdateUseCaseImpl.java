package com.publicworks.public_works_management.bids.application.usecase;

import com.publicworks.public_works_management.bids.application.command.CreateBidCommand;
import com.publicworks.public_works_management.bids.application.dto.BidResponse;
import com.publicworks.public_works_management.bids.application.mappers.BidMapper;
import com.publicworks.public_works_management.bids.application.ports.input.BidService;
import com.publicworks.public_works_management.bids.application.ports.input.BidUpdateUseCase;
import com.publicworks.public_works_management.bids.domain.entities.Bid;
import com.publicworks.public_works_management.bids.domain.exceptions.BidNotFoundException;
import com.publicworks.public_works_management.bids.domain.exceptions.BidValidationException;
import com.publicworks.public_works_management.bids.domain.valueobjects.BidId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BidUpdateUseCaseImpl implements BidUpdateUseCase {
    private final BidService bidService;
    private final BidMapper bidMapper;

    @Override
    public BidResponse execute(CreateBidCommand command, BidId id) throws BidValidationException, BidNotFoundException {
        Bid bidToUpdate = bidService.getBidById(id)
                .orElseThrow(() -> new BidNotFoundException("Bid Not Found"));
        Bid updatedFields = bidMapper.commandToEntity(command);
        bidToUpdate.update(updatedFields);

        Bid updateBid = bidService.updateBid(bidToUpdate);

        return bidMapper.toResponse(updateBid);
    }
}
