package com.publicworks.public_works_management.contracts.application.usecase.command;

import com.publicworks.public_works_management.contracts.application.ports.input.command.ApproveContractUseCase;
import com.publicworks.public_works_management.contracts.application.ports.input.command.ContractService;
import com.publicworks.public_works_management.contracts.domain.Contract;
import com.publicworks.public_works_management.contracts.domain.exceptions.ContractNotFoundException;
import com.publicworks.public_works_management.contracts.domain.exceptions.ContractValidationException;
import com.publicworks.public_works_management.contracts.domain.exceptions.IllegalStatusTransitionException;
import com.publicworks.public_works_management.contracts.domain.valueObjects.ContractId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ApproveContractUseCaseImpl implements ApproveContractUseCase {
    private final ContractService contractService;

    @Override
    public void execute(String contractId) throws ContractValidationException, IllegalStatusTransitionException, ContractNotFoundException {
        Contract contract = contractService.getContractById(ContractId.from(contractId));

        contract.approve();

        contractService.updateContract(contract);
    }
}
