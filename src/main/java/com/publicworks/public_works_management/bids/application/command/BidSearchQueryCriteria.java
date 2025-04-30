package com.publicworks.public_works_management.bids.application.command;

import com.publicworks.public_works_management.bids.domain.valueobjects.BidStatus;
import com.publicworks.public_works_management.bids.infrastructure.persistence.BidModel;
import com.publicworks.public_works_management.contracts.infrastructure.ports.output.persistence.ContractModel;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public record BidSearchQueryCriteria(
        BidStatus status,
        LocalDate fromDate,
        LocalDate toDate
) {

    public Specification<BidModel> generateSpecification() {
        return null;
    }


}