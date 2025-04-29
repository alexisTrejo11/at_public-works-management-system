package com.publicworks.public_works_management.contracts.application.ports.input.command;

import com.publicworks.public_works_management.contracts.domain.exceptions.ContractNotFoundException;
import com.publicworks.public_works_management.contracts.domain.exceptions.ContractValidationException;
import com.publicworks.public_works_management.contracts.domain.exceptions.IllegalStatusTransitionException;

public interface ApproveContractUseCase {
    void execute(String contractId)
            throws ContractValidationException, IllegalStatusTransitionException, ContractNotFoundException;
}
