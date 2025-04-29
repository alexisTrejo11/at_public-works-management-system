package com.publicworks.public_works_management.contracts.application.usecase;

import com.publicworks.public_works_management.contracts.application.ports.input.command.ApproveContractUseCase;
import com.publicworks.public_works_management.contracts.domain.exceptions.ContractNotFoundException;
import com.publicworks.public_works_management.contracts.domain.exceptions.ContractValidationException;
import com.publicworks.public_works_management.contracts.domain.exceptions.IllegalStatusTransitionException;
import org.springframework.stereotype.Component;

@Component
public class ApproveContractUseCaseImpl implements ApproveContractUseCase {
    @Override
    public void execute(String contractId) throws ContractValidationException, IllegalStatusTransitionException, ContractNotFoundException {

    }
}
