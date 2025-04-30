package com.publicworks.public_works_management.bids.domain.entities;

import com.publicworks.public_works_management.bids.domain.exceptions.BidModificationException;
import com.publicworks.public_works_management.bids.domain.exceptions.BidValidationException;
import com.publicworks.public_works_management.bids.domain.valueobjects.BidDocument;
import com.publicworks.public_works_management.bids.domain.valueobjects.BidId;
import com.publicworks.public_works_management.bids.domain.valueobjects.BidStatus;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Bid {
    @Setter
    private BidId id;
    private final String code;
    private final String name;
    private final String description;
    private final LocalDate openingDate;
    private final LocalDate closingDate;
    private final BigDecimal budget;
    private final BidStatus status;
    private final List<BidDocument> documents;

    public static Bid create(String code, String name, LocalDate openingDate,
                             LocalDate closingDate, BigDecimal budget) {
        validateDates(openingDate, closingDate);
        validateBudget(budget);

        return Bid.builder()
                .id(BidId.generate())
                .code(code)
                .name(name)
                .openingDate(openingDate)
                .closingDate(closingDate)
                .budget(budget)
                .status(BidStatus.DRAFT)
                .documents(Collections.emptyList())
                .build();
    }

    public boolean isOngoing() {
        return LocalDate.now().isBefore(this.closingDate)
                && (this.status != BidStatus.AWARDED && this.status != BidStatus.REJECTED);
    }

    public void validateFields() {

    }

    public void update(Bid bidChanges) {

    }

    public void addDocument(BidDocument document) {
        if (status != BidStatus.DRAFT) {
            throw new BidModificationException(
                    "Cannot add documents to bid in " + status + " status");
        }
        documents.add(document);
    }

    public List<BidDocument> getDocuments() {
        return Collections.unmodifiableList(documents);
    }

    private static void validateDates(LocalDate opening, LocalDate closing) {
        if (closing.isBefore(opening)) {
            throw new BidValidationException("Closing date must be after opening date");
        }
    }

    private static void validateBudget(BigDecimal budget) {
        if (budget.compareTo(BigDecimal.ZERO) <= 0) {
            throw new BidValidationException("Budget must be positive");
        }
    }

}