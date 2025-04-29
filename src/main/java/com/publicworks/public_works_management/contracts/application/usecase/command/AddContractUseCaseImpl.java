package com.publicworks.public_works_management.contracts.application.usecase.command;

import com.publicworks.public_works_management.contracts.application.dto.request.ContractClauseRequest;
import com.publicworks.public_works_management.contracts.application.mapper.ContractClauseMappers;
import com.publicworks.public_works_management.contracts.application.ports.input.command.AddContractClauseUseCase;
import com.publicworks.public_works_management.contracts.application.ports.input.command.ContractClauseService;
import com.publicworks.public_works_management.contracts.application.ports.input.command.ContractService;
import com.publicworks.public_works_management.contracts.application.ports.output.ContractClauseJPARepository;
import com.publicworks.public_works_management.contracts.domain.Contract;
import com.publicworks.public_works_management.contracts.domain.ContractClause;
import com.publicworks.public_works_management.contracts.domain.exceptions.ContractModificationException;
import com.publicworks.public_works_management.contracts.domain.exceptions.ContractNotFoundException;
import com.publicworks.public_works_management.contracts.domain.valueObjects.ContractId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AddContractUseCaseImpl implements AddContractClauseUseCase {
    private final ContractClauseService clauseService;
    private final ContractService contractService;
    private final ContractClauseMappers clauseMappers;

    @Override
    public void execute(ContractClauseRequest clauseRequest) throws ContractModificationException, ContractNotFoundException {
        Contract contract = contractService.getContractById(ContractId.from(clauseRequest.getContractId()));
        ContractClause clause = clauseMappers.requestDTOToDomain(clauseRequest);

        contract.addClause(clause);

        clauseService.createContractClause(clause);
    }
}
