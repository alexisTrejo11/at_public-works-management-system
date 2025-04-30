package com.publicworks.public_works_management.bids.infrastructure.ports.input.rest.api.controllers;

import com.publicworks.public_works_management.bids.application.command.BidSearchQueryCriteria;
import com.publicworks.public_works_management.bids.application.dto.BidResponse;
import com.publicworks.public_works_management.bids.application.ports.input.BidQueryUseCase;
import com.publicworks.public_works_management.bids.domain.valueobjects.BidId;
import com.publicworks.public_works_management.shared.page.PageInput;
import com.publicworks.public_works_management.shared.response.http.ResponseWrapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bids")
@RequiredArgsConstructor
public class BidQueryController {
    private final BidQueryUseCase bidQueryUseCase;

    @GetMapping
    public ResponseWrapper<List<BidResponse>> getAllCurrentBids() {
        List<BidResponse> bids = bidQueryUseCase.getAllCurrentBids();
        return ResponseWrapper.found(bids, "Current Bids");
    }

    @GetMapping("/{id}")
    public ResponseWrapper<BidResponse> getBidById(@RequestParam @Valid String id) {
        BidId bidId = new BidId(id);

        BidResponse bidResponse = bidQueryUseCase.getBidById(bidId);

        return ResponseWrapper.found(bidResponse, "Bid");
    }

    @GetMapping("/{id}/track-record")
    public ResponseWrapper<BidResponse> getBidTrackRecordById(@RequestParam @Valid String id) {
        BidId bidId = new BidId(id);

        BidResponse bidResponse = bidQueryUseCase.getBidById(bidId);

        return ResponseWrapper.found(bidResponse, "Bid");
    }

    @GetMapping("/search")
    public ResponseWrapper<Page<BidResponse>> searchBids(@ModelAttribute PageInput pageInput,
                                                   @ModelAttribute BidSearchQueryCriteria bidSearchQueryCriteria) {
        Page<BidResponse> bidResponsePage = bidQueryUseCase.searchBids(bidSearchQueryCriteria, pageInput);

        return ResponseWrapper.found(bidResponsePage, "Bid");
    }
}
