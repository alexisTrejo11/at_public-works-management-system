package com.publicworks.public_works_management.contracts.application.ports.input.query;

import com.publicworks.public_works_management.contracts.application.dto.response.ContractSummary;
import com.publicworks.public_works_management.contracts.domain.valueObjects.ContractId;

import java.util.Optional;

public interface GetContractSummaryUseCase {
    Optional<ContractSummary> getContractSummary(ContractId id);
}