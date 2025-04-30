package com.publicworks.public_works_management.bids.application.usecase;

import com.publicworks.public_works_management.bids.application.command.BidSearchQueryCriteria;
import com.publicworks.public_works_management.bids.application.dto.BidResponse;
import com.publicworks.public_works_management.bids.application.mappers.BidMapper;
import com.publicworks.public_works_management.bids.application.ports.input.BidQueryUseCase;
import com.publicworks.public_works_management.bids.application.ports.input.BidService;
import com.publicworks.public_works_management.bids.domain.entities.Bid;
import com.publicworks.public_works_management.bids.domain.exceptions.BidNotFoundException;
import com.publicworks.public_works_management.bids.domain.valueobjects.BidId;
import com.publicworks.public_works_management.shared.page.PageInput;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BidQueryUseCaseImpl implements BidQueryUseCase {
    private final BidService bidService;
    private BidMapper bidMapper;

    @Override
    public BidResponse getBidById(BidId bidId) throws BidNotFoundException {
        return bidService.getBidById(bidId)
                .map(bidMapper::toResponse)
                .orElseThrow(() -> new BidNotFoundException("Bid Not Found"));
    }

    @Override
    public List<BidResponse> getAllCurrentBids() {
        return bidService.getAllBids().stream()
                .filter(Bid::isOngoing)
                .map(bidMapper::toResponse)
                .toList();
    }

    @Override
    public Page<BidResponse> searchBids(BidSearchQueryCriteria query, PageInput pageInput) {
        return bidService.searchBids(query, pageInput)
                .map(bidMapper::toResponse);
    }
}
