package com.publicworks.public_works_management.contracts.application.ports.input.query;

import com.publicworks.public_works_management.contracts.domain.exceptions.ContractNotFoundException;
import com.publicworks.public_works_management.contracts.domain.valueObjects.ContractId;

public interface SoftDeleteContractUseCase {
    void execute(ContractId contractId)
            throws ContractNotFoundException;
}

