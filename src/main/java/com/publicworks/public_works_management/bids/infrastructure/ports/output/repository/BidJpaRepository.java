package com.publicworks.public_works_management.bids.infrastructure.ports.output.repository;

import com.publicworks.public_works_management.bids.infrastructure.persistence.BidModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface BidJpaRepository extends JpaRepository<BidModel, UUID>, JpaSpecificationExecutor<BidModel> {
}
