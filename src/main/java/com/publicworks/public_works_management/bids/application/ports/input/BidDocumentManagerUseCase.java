package com.publicworks.public_works_management.bids.application.ports.input;

import com.publicworks.public_works_management.bids.application.command.AddDocumentCommand;
import com.publicworks.public_works_management.bids.domain.exceptions.BidModificationException;
import com.publicworks.public_works_management.bids.domain.exceptions.DocumentValidationException;

public interface BidDocumentManagerUseCase {
    void addDocument(AddDocumentCommand command)
            throws BidModificationException, DocumentValidationException;
}