package com.publicworks.public_works_management.bids.application.usecase;

import com.publicworks.public_works_management.bids.application.ports.input.BidDeleteUseCase;
import com.publicworks.public_works_management.bids.application.ports.input.BidService;
import com.publicworks.public_works_management.bids.domain.entities.Bid;
import com.publicworks.public_works_management.bids.domain.exceptions.BidNotFoundException;
import com.publicworks.public_works_management.bids.domain.valueobjects.BidId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BidDeleteUseCaseImpl implements BidDeleteUseCase {
    private final BidService bidService;

    @Override
    public void execute(BidId id) throws BidNotFoundException {
        Bid bid = bidService.getBidById(id)
                .orElseThrow(() -> new BidNotFoundException("Bid Not Found"));

        bidService.deleteBid(bid);
    }
}
