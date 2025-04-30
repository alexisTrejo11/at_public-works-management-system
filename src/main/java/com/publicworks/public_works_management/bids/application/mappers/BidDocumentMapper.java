package com.publicworks.public_works_management.bids.application.mappers;

import com.publicworks.public_works_management.bids.application.command.AddDocumentCommand;
import com.publicworks.public_works_management.bids.application.dto.DocumentResponse;
import com.publicworks.public_works_management.bids.domain.entities.Bid;
import com.publicworks.public_works_management.bids.domain.valueobjects.BidDocument;
import com.publicworks.public_works_management.bids.infrastructure.persistence.BidDocumentModel;
import com.publicworks.public_works_management.bids.infrastructure.persistence.BidModel;
import org.mapstruct.*;

public interface BidDocumentMapper {
    // Document mappings
    @Mapping(target = "content", ignore = true)
    BidDocumentModel documentToEntity(BidDocument document);
    BidDocument entityToDocument(BidDocumentModel entity);

    @Mapping(target = "storagePath", source = "content")
    BidDocument addCommandToDocument(AddDocumentCommand command);

    @Mapping(target = "documentId", source = "id")
    DocumentResponse documentToResponse(BidDocument document);

    @AfterMapping
    default void handleDocumentContent(
            @MappingTarget BidDocumentModel entity,
            AddDocumentCommand command
    ) {
        entity.setContent(command.content());
    }
}
