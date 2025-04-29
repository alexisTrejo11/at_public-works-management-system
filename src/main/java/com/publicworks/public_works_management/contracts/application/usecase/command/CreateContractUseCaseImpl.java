package com.publicworks.public_works_management.contracts.application.usecase.command;

import com.publicworks.public_works_management.contracts.application.dto.response.ContractResponse;
import com.publicworks.public_works_management.contracts.application.mapper.ContractMappers;
import com.publicworks.public_works_management.contracts.application.ports.input.command.ContractService;
import com.publicworks.public_works_management.contracts.application.ports.input.command.CreateContractUseCase;
import com.publicworks.public_works_management.contracts.application.ports.input.query.SoftDeleteContractUseCase;
import com.publicworks.public_works_management.contracts.application.service.CreateContractCommand;
import com.publicworks.public_works_management.contracts.domain.Contract;
import com.publicworks.public_works_management.contracts.domain.exceptions.ContractValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateContractUseCaseImpl implements CreateContractUseCase {
    private final ContractService contractService;
    private final ContractMappers contractMappers;

    @Override
    public ContractResponse execute(CreateContractCommand command) throws ContractValidationException {
        Contract contract = contractMappers.createCommandToEntity(command);
        contract.validateFields();

        contract = contractService.createContract(contract);

        return contractMappers.domainToResponseDTO(contract);
    }
}
