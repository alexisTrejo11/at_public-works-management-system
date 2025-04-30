package com.publicworks.public_works_management.bids.infrastructure.ports.input.rest.api.controllers;

import com.publicworks.public_works_management.bids.application.command.CreateBidCommand;
import com.publicworks.public_works_management.bids.application.dto.BidResponse;
import com.publicworks.public_works_management.bids.application.dto.CreateBidRequest;
import com.publicworks.public_works_management.bids.application.ports.input.BidCreatorUseCase;
import com.publicworks.public_works_management.bids.application.ports.input.BidDeleteUseCase;
import com.publicworks.public_works_management.bids.application.ports.input.BidUpdateUseCase;
import com.publicworks.public_works_management.bids.domain.valueobjects.BidId;
import com.publicworks.public_works_management.shared.response.http.ResponseWrapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/bids")
public class BidCommandController {
    private final BidCreatorUseCase bidCreatorUseCase;
    private final BidUpdateUseCase bidUpdateUseCase;
    private final BidDeleteUseCase bidDeleteUseCase;

    @PostMapping
    public ResponseWrapper<BidResponse> createBid(@RequestBody @Valid CreateBidRequest bidRequest) {
        CreateBidCommand bidCommand = CreateBidCommand.fromRequest(bidRequest);
        BidResponse bidCreated = bidCreatorUseCase.execute(bidCommand);

        return ResponseWrapper.created(bidCreated, "Bid");
    }

    @PatchMapping
    public ResponseWrapper<BidResponse> updateBid(@RequestBody @Valid CreateBidRequest bidRequest, @Valid String id) {
        BidId bidId = new BidId(id);
        CreateBidCommand bidCommand = CreateBidCommand.fromRequest(bidRequest);

        BidResponse bidUpdated = bidUpdateUseCase.execute(bidCommand, bidId);

        return ResponseWrapper.updated(bidUpdated, "Bid");
    }

    @DeleteMapping("/{id}")
    public ResponseWrapper<BidResponse> deleteBid(@RequestParam @Valid String id) {
        BidId bidId = new BidId(id);

        bidDeleteUseCase.execute(bidId);

        return ResponseWrapper.deleted("Bid");
    }
}