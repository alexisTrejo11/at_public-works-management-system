package com.publicworks.public_works_management.contracts.domain;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ContractClause {
    String clauseNumber;
    String title;
    String content;
    boolean mandatory;

    public boolean isComplianceRequired() {
        return this.mandatory;
    }
}