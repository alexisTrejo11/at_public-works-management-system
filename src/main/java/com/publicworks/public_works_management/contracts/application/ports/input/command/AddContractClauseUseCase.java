package com.publicworks.public_works_management.contracts.application.ports.input.command;

import com.publicworks.public_works_management.contracts.application.dto.request.ContractClauseRequest;
import com.publicworks.public_works_management.contracts.domain.exceptions.ContractModificationException;
import com.publicworks.public_works_management.contracts.domain.exceptions.ContractNotFoundException;

public interface AddContractClauseUseCase {
    void execute(ContractClauseRequest clauseRequest)
            throws ContractModificationException, ContractNotFoundException;
}