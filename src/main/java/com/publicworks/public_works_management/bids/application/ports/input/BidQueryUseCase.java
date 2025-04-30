package com.publicworks.public_works_management.bids.application.ports.input;

import com.publicworks.public_works_management.bids.application.command.BidSearchQueryCriteria;
import com.publicworks.public_works_management.bids.application.dto.BidResponse;
import com.publicworks.public_works_management.bids.domain.exceptions.BidNotFoundException;
import com.publicworks.public_works_management.bids.domain.valueobjects.BidId;
import com.publicworks.public_works_management.shared.page.PageInput;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BidQueryUseCase {
    BidResponse getBidById(BidId bidId) throws BidNotFoundException;
    List<BidResponse> getAllCurrentBids();
    Page<BidResponse> searchBids(BidSearchQueryCriteria query, PageInput pageInput);
}
