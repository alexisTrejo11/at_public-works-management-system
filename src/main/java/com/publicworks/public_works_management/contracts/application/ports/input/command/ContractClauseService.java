package com.publicworks.public_works_management.contracts.application.ports.input.command;

import com.publicworks.public_works_management.contracts.domain.ContractClause;

import java.util.List;

public interface ContractClauseService {
    ContractClause getContractClauseById(Long id);
    List<ContractClause> getAllContractClauses();
    ContractClause createContractClause(ContractClause contractClause);
    ContractClause updateContractClause(Long id, ContractClause contractClause);
    void deleteContractClause(Long id);
}
