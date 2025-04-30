package com.publicworks.public_works_management.bids.application.services;

import com.publicworks.public_works_management.bids.application.command.BidSearchQueryCriteria;
import com.publicworks.public_works_management.bids.application.mappers.BidMapper;
import com.publicworks.public_works_management.bids.application.ports.input.BidService;
import com.publicworks.public_works_management.bids.domain.entities.Bid;
import com.publicworks.public_works_management.bids.domain.exceptions.BidNotFoundException;
import com.publicworks.public_works_management.bids.domain.valueobjects.BidDocument;
import com.publicworks.public_works_management.bids.domain.valueobjects.BidId;
import com.publicworks.public_works_management.bids.infrastructure.persistence.BidModel;
import com.publicworks.public_works_management.bids.infrastructure.ports.output.repository.BidJpaRepository;
import com.publicworks.public_works_management.shared.page.PageInput;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BidServiceImpl implements BidService {

    private final BidJpaRepository bidRepository;
    private final BidMapper bidMapper;

    @CacheEvict(value = "bids", allEntries = true)
    public Bid createBid(Bid newBid) {
        newBid.validateFields();
        BidModel bidModel = bidRepository.save(bidMapper.toEntity(newBid));
        return bidMapper.toDomain(bidModel);
    }

    @Cacheable(value = "bids", key = "#id.value()")
    public Optional<Bid> getBidById(BidId id) {
        Optional<BidModel> optionalBidModel = bidRepository.findById(id.toUUID());
        return optionalBidModel.map(bidMapper::toDomain);
    }

    @Cacheable("allBids")
    public List<Bid> getAllBids() {
        List<BidModel> bidModels = bidRepository.findAll();
        return bidModels.stream()
                .map(bidMapper::toDomain)
                .toList();
    }

    @Override
    public Page<Bid> searchBids(BidSearchQueryCriteria query, PageInput pageInput) {
        Pageable pageable = PageRequest.of(pageInput.pageNumber(), pageInput.pageSize());
        Specification<BidModel> querySpecification = query.generateSpecification();

        Page<BidModel> bidModels = bidRepository.findAll(querySpecification, pageable);

        return bidModels.map(bidMapper::toDomain);
    }


    @CachePut(value = "bids", key = "#bidUpdated.id.value()")
    @CacheEvict(value = "allBids", allEntries = true)
    public Bid updateBid(Bid bidUpdated) {
        BidModel bidModel = this.getBidModel(bidUpdated.getId());
        bidMapper.updateEntityFromDomain(bidModel, bidUpdated);
        bidRepository.saveAndFlush(bidModel);
        return bidMapper.toDomain(bidModel);
    }

    @CacheEvict(value = {"bids", "allBids"}, allEntries = true)
    public void deleteBid(Bid bid) {
        BidModel bidModel = bidMapper.toEntity(bid);
        bidRepository.delete(bidModel);
    }


    @CachePut(value = "bids", key = "#id.value()")
    @CacheEvict(value = "allBids", allEntries = true)
    public Bid addDocumentToBid(BidId id, BidDocument document) {
        BidModel existingBid = this.getBidModel(id);

        // existingBid.addDocument(document);
        bidRepository.saveAndFlush(existingBid);

        return bidMapper.toDomain(existingBid);
    }

    private BidModel getBidModel(BidId id) {
        return bidRepository.findById(id.toUUID())
                .orElseThrow(() -> new BidNotFoundException("Bid with id " + id.value() + " not found"));
    }
}