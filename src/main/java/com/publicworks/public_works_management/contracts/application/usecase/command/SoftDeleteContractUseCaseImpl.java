package com.publicworks.public_works_management.contracts.application.usecase.command;

import com.publicworks.public_works_management.contracts.application.mapper.ContractMappers;
import com.publicworks.public_works_management.contracts.application.ports.input.command.ContractService;
import com.publicworks.public_works_management.contracts.application.ports.input.query.SoftDeleteContractUseCase;
import com.publicworks.public_works_management.contracts.domain.Contract;
import com.publicworks.public_works_management.contracts.domain.exceptions.ContractNotFoundException;
import com.publicworks.public_works_management.contracts.domain.valueObjects.ContractId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SoftDeleteContractUseCaseImpl implements SoftDeleteContractUseCase {
    private final ContractService contractService;

    @Override
    public void execute(ContractId contractId) throws ContractNotFoundException {
        Contract contract = contractService.getContractById(contractId);

        contractService.deleteContract(contract.getId());
    }
}
