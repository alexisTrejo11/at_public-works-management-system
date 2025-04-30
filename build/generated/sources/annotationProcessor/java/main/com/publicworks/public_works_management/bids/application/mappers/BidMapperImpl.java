package com.publicworks.public_works_management.bids.application.mappers;

import com.publicworks.public_works_management.bids.application.command.CreateBidCommand;
import com.publicworks.public_works_management.bids.application.dto.BidResponse;
import com.publicworks.public_works_management.bids.application.dto.BidSummaryResponse;
import com.publicworks.public_works_management.bids.application.dto.DocumentResponse;
import com.publicworks.public_works_management.bids.domain.entities.Bid;
import com.publicworks.public_works_management.bids.domain.valueobjects.BidDocument;
import com.publicworks.public_works_management.bids.infrastructure.persistence.BidDocumentModel;
import com.publicworks.public_works_management.bids.infrastructure.persistence.BidModel;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-29T16:57:51-0600",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.13.jar, environment: Java 21.0.7 (Eclipse Adoptium)"
)
@Component
public class BidMapperImpl implements BidMapper {

    @Override
    public Bid commandToEntity(CreateBidCommand command) {
        if ( command == null ) {
            return null;
        }

        Bid.BidBuilder bid = Bid.builder();

        return bid.build();
    }

    @Override
    public BidModel toEntity(Bid bid) {
        if ( bid == null ) {
            return null;
        }

        BidModel bidModel = new BidModel();

        bidModel.setId( bidIdToUuid( bid.getId() ) );
        bidModel.setCode( bid.getCode() );
        bidModel.setName( bid.getName() );
        bidModel.setDescription( bid.getDescription() );
        bidModel.setOpeningDate( bid.getOpeningDate() );
        bidModel.setClosingDate( bid.getClosingDate() );
        bidModel.setBudget( bid.getBudget() );
        bidModel.setStatus( bid.getStatus() );

        return bidModel;
    }

    @Override
    public Bid toDomain(BidModel entity) {
        if ( entity == null ) {
            return null;
        }

        Bid.BidBuilder bid = Bid.builder();

        bid.id( uuidToBidId( entity.getId() ) );
        bid.code( entity.getCode() );
        bid.name( entity.getName() );
        bid.description( entity.getDescription() );
        bid.openingDate( entity.getOpeningDate() );
        bid.closingDate( entity.getClosingDate() );
        bid.budget( entity.getBudget() );
        bid.status( entity.getStatus() );

        return bid.build();
    }

    @Override
    public BidResponse toResponse(Bid bid) {
        if ( bid == null ) {
            return null;
        }

        String id = null;
        String code = null;
        String name = null;
        String description = null;
        LocalDate openingDate = null;
        LocalDate closingDate = null;
        BigDecimal budget = null;
        String status = null;
        List<DocumentResponse> documents = null;

        id = bidIdToString( bid.getId() );
        code = bid.getCode();
        name = bid.getName();
        description = bid.getDescription();
        openingDate = bid.getOpeningDate();
        closingDate = bid.getClosingDate();
        budget = bid.getBudget();
        if ( bid.getStatus() != null ) {
            status = bid.getStatus().name();
        }
        documents = bidDocumentListToDocumentResponseList( bid.getDocuments() );

        BidResponse bidResponse = new BidResponse( id, code, name, description, openingDate, closingDate, budget, status, documents );

        return bidResponse;
    }

    @Override
    public BidSummaryResponse toSummaryResponse(Bid bid) {
        if ( bid == null ) {
            return null;
        }

        String id = null;
        String code = null;
        String name = null;
        LocalDate openingDate = null;
        LocalDate closingDate = null;
        BigDecimal budget = null;
        String status = null;

        id = bidIdToString( bid.getId() );
        code = bid.getCode();
        name = bid.getName();
        openingDate = bid.getOpeningDate();
        closingDate = bid.getClosingDate();
        budget = bid.getBudget();
        if ( bid.getStatus() != null ) {
            status = bid.getStatus().name();
        }

        BidSummaryResponse bidSummaryResponse = new BidSummaryResponse( id, code, name, openingDate, closingDate, budget, status );

        return bidSummaryResponse;
    }

    @Override
    public void updateEntityFromDomain(BidModel entity, Bid bid) {
        if ( bid == null ) {
            return;
        }

        if ( bid.getCode() != null ) {
            entity.setCode( bid.getCode() );
        }
        if ( bid.getName() != null ) {
            entity.setName( bid.getName() );
        }
        if ( bid.getDescription() != null ) {
            entity.setDescription( bid.getDescription() );
        }
        if ( bid.getOpeningDate() != null ) {
            entity.setOpeningDate( bid.getOpeningDate() );
        }
        if ( bid.getClosingDate() != null ) {
            entity.setClosingDate( bid.getClosingDate() );
        }
        if ( bid.getBudget() != null ) {
            entity.setBudget( bid.getBudget() );
        }
        if ( bid.getStatus() != null ) {
            entity.setStatus( bid.getStatus() );
        }
        if ( entity.getDocuments() != null ) {
            List<BidDocumentModel> list = bidDocumentListToBidDocumentModelList( bid.getDocuments() );
            if ( list != null ) {
                entity.getDocuments().clear();
                entity.getDocuments().addAll( list );
            }
        }
        else {
            List<BidDocumentModel> list = bidDocumentListToBidDocumentModelList( bid.getDocuments() );
            if ( list != null ) {
                entity.setDocuments( list );
            }
        }
    }

    protected DocumentResponse bidDocumentToDocumentResponse(BidDocument bidDocument) {
        if ( bidDocument == null ) {
            return null;
        }

        String name = null;
        String type = null;

        name = bidDocument.getName();
        type = bidDocument.getType();

        String id = null;

        DocumentResponse documentResponse = new DocumentResponse( id, name, type );

        return documentResponse;
    }

    protected List<DocumentResponse> bidDocumentListToDocumentResponseList(List<BidDocument> list) {
        if ( list == null ) {
            return null;
        }

        List<DocumentResponse> list1 = new ArrayList<DocumentResponse>( list.size() );
        for ( BidDocument bidDocument : list ) {
            list1.add( bidDocumentToDocumentResponse( bidDocument ) );
        }

        return list1;
    }

    protected BidDocumentModel bidDocumentToBidDocumentModel(BidDocument bidDocument) {
        if ( bidDocument == null ) {
            return null;
        }

        BidDocumentModel.BidDocumentModelBuilder bidDocumentModel = BidDocumentModel.builder();

        bidDocumentModel.name( bidDocument.getName() );
        bidDocumentModel.type( bidDocument.getType() );
        bidDocumentModel.storagePath( bidDocument.getStoragePath() );

        return bidDocumentModel.build();
    }

    protected List<BidDocumentModel> bidDocumentListToBidDocumentModelList(List<BidDocument> list) {
        if ( list == null ) {
            return null;
        }

        List<BidDocumentModel> list1 = new ArrayList<BidDocumentModel>( list.size() );
        for ( BidDocument bidDocument : list ) {
            list1.add( bidDocumentToBidDocumentModel( bidDocument ) );
        }

        return list1;
    }
}
