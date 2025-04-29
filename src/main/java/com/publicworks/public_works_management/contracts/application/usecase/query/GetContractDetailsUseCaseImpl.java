package com.publicworks.public_works_management.contracts.application.usecase.query;

import com.publicworks.public_works_management.contracts.application.dto.response.ContractResponse;
import com.publicworks.public_works_management.contracts.application.mapper.ContractMappers;
import com.publicworks.public_works_management.contracts.application.ports.input.command.ContractService;
import com.publicworks.public_works_management.contracts.application.ports.input.query.GetContractDetailsUseCase;
import com.publicworks.public_works_management.contracts.domain.Contract;
import com.publicworks.public_works_management.contracts.domain.exceptions.ContractNotFoundException;
import com.publicworks.public_works_management.contracts.domain.valueObjects.ContractId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetContractDetailsUseCaseImpl implements GetContractDetailsUseCase {
    private final ContractService contractService;
    private final ContractMappers contractMappers;

    @Override
    public ContractResponse execute(ContractId contractId) throws ContractNotFoundException {
        Contract contract = contractService.getContractById(contractId);
        return contractMappers.domainToResponseDTO(contract);
    }
}
