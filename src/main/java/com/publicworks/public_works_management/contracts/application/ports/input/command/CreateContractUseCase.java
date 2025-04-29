package com.publicworks.public_works_management.contracts.application.ports.input.command;

import com.publicworks.public_works_management.contracts.application.dto.response.ContractResponse;
import com.publicworks.public_works_management.contracts.application.service.CreateContractCommand;
import com.publicworks.public_works_management.contracts.domain.exceptions.ContractValidationException;

public interface CreateContractUseCase {
    ContractResponse execute(CreateContractCommand command)
            throws ContractValidationException;
}