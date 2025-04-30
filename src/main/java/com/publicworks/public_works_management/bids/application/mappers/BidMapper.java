package com.publicworks.public_works_management.bids.application.mappers;

import com.publicworks.public_works_management.bids.application.command.CreateBidCommand;
import com.publicworks.public_works_management.bids.domain.entities.Bid;
import com.publicworks.public_works_management.bids.domain.valueobjects.BidId;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import com.publicworks.public_works_management.bids.application.dto.BidResponse;
import com.publicworks.public_works_management.bids.application.dto.BidSummaryResponse;
import com.publicworks.public_works_management.bids.infrastructure.persistence.BidModel;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface BidMapper {
    BidMapper INSTANCE = Mappers.getMapper(BidMapper.class);

    // Command -> Domain Entity
    @BeanMapping(ignoreByDefault = true)
    @Mappings({})
    Bid commandToEntity(CreateBidCommand command);

    // Domain -> Entity
    @Mapping(source = "id", target = "id", qualifiedByName = "bidIdToUuid")
    @Mapping(target = "documents", ignore = true) // Ignoramos por ahora
    @Mapping(target = "contract", ignore = true)  // Ignoramos campo ausente
    BidModel toEntity(Bid bid);

    // Entity -> Domain
    @Mapping(source = "id", target = "id", qualifiedByName = "uuidToBidId")
    @Mapping(target = "documents", ignore = true) // Ignoramos por ahora
    Bid toDomain(BidModel entity);

    @Named("bidIdToUuid")
    default UUID bidIdToUuid(BidId bidId) {
        return bidId != null ? bidId.toUUID() : null;
    }

    @Named("uuidToBidId")
    default BidId uuidToBidId(UUID uuid) {
        return uuid != null ? new BidId(uuid.toString()) : null;
    }

    @Named("bidIdToString")
    default String bidIdToString(BidId bidId) {
        return bidId != null ? bidId.value() : null;
    }

    @Mapping(source = "id", target = "id", qualifiedByName = "bidIdToString")
    BidResponse toResponse(Bid bid);

    @Mapping(source = "id", target = "id", qualifiedByName = "bidIdToString")
    BidSummaryResponse toSummaryResponse(Bid bid);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "contract", ignore = true)
    void updateEntityFromDomain(@MappingTarget BidModel entity, Bid bid);

    @AfterMapping
    default void generateIdIfMissing(@MappingTarget Bid bid) {
        if (bid.getId() == null) {
            bid.setId(new BidId(UUID.randomUUID().toString()));
        }
    }
}