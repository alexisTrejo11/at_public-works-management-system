package com.publicworks.public_works_management.bids.application.ports.input;

import com.publicworks.public_works_management.bids.application.command.BidSearchQueryCriteria;
import com.publicworks.public_works_management.bids.domain.entities.Bid;
import com.publicworks.public_works_management.bids.domain.valueobjects.BidDocument;
import com.publicworks.public_works_management.bids.domain.valueobjects.BidId;
import com.publicworks.public_works_management.shared.page.PageInput;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface BidService {

    Bid createBid(Bid newBid);
    Optional<Bid> getBidById(BidId id);
    List<Bid> getAllBids();
    Page<Bid> searchBids(BidSearchQueryCriteria query, PageInput pageInput);
    Bid updateBid(Bid bidUpdated);
    void deleteBid(Bid bid);
    Bid addDocumentToBid(BidId id, BidDocument document);
}