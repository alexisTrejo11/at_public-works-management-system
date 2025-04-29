package com.publicworks.public_works_management.contracts.application.usecase;

import com.publicworks.public_works_management.contracts.application.dto.response.ContractSummary;
import com.publicworks.public_works_management.contracts.application.mapper.ContractMappers;
import com.publicworks.public_works_management.contracts.application.ports.input.command.ContractService;
import com.publicworks.public_works_management.contracts.domain.Contract;
import com.publicworks.public_works_management.contracts.domain.exceptions.ContractNotFoundException;
import com.publicworks.public_works_management.contracts.domain.valueObjects.ContractId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GenerateReportUseCaseImpl {
    private final ContractService contractService;

    public ContractSummary execute(ContractId contractId) throws ContractNotFoundException {
        Contract contract = contractService.getContractById(contractId);

        return contractService.generateReport(contract);
    }
}
