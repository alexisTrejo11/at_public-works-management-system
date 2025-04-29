package com.publicworks.public_works_management.contracts.application.service;

import com.publicworks.public_works_management.contracts.application.mapper.ContractClauseMappers;
import com.publicworks.public_works_management.contracts.application.ports.input.command.ContractClauseService;
import com.publicworks.public_works_management.contracts.application.ports.output.ContractClauseJPARepository;
import com.publicworks.public_works_management.contracts.application.ports.output.ContractJPARepository;
import com.publicworks.public_works_management.contracts.domain.ContractClause;
import com.publicworks.public_works_management.contracts.domain.exceptions.ContractClauseNotFoundException;
import com.publicworks.public_works_management.contracts.infrastructure.ports.output.persistence.ContractClauseModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContractClauseServiceImpl implements ContractClauseService {

    private final ContractClauseJPARepository contractClauseJPARepository;
    private final ContractJPARepository contractJPARepository;
    private final ContractClauseMappers contractClauseMappers;

    public ContractClause getContractClauseById(Long id) {
        Optional<ContractClauseModel> optionalContractClauseModel = contractClauseJPARepository.findById(id);
        return optionalContractClauseModel
                .map(contractClauseMappers::modelToDomain)
                .orElseThrow(() -> new ContractClauseNotFoundException("ID", id));
    }

    public List<ContractClause> getAllContractClauses() {
        return contractClauseJPARepository.findAll().stream()
                .map(contractClauseMappers::modelToDomain)
                .collect(Collectors.toList());
    }

    @Transactional
    public ContractClause createContractClause(ContractClause contractClause) {
        ContractClauseModel contractClauseModel = contractClauseMappers.domainToModel(contractClause);
        ContractClauseModel savedContractClauseModel = contractClauseJPARepository.save(contractClauseModel);
        return contractClauseMappers.modelToDomain(savedContractClauseModel);
    }

    @Transactional
    public ContractClause updateContractClause(Long id, ContractClause contractClause) {
        Optional<ContractClauseModel> optionalContractClauseModel = contractClauseJPARepository.findById(id);
        if (optionalContractClauseModel.isEmpty()) {
            throw new ContractClauseNotFoundException("ID", id);
        }
        ContractClauseModel existingContractClauseModel = optionalContractClauseModel.get();
        contractClauseMappers.updateModelFromDomain(contractClause, existingContractClauseModel); // Assuming you have this mapper method
        ContractClauseModel updatedContractClauseModel = contractClauseJPARepository.save(existingContractClauseModel);
        return contractClauseMappers.modelToDomain(updatedContractClauseModel);
    }

    @Transactional
    public void deleteContractClause(Long id) {
        if (!contractClauseJPARepository.existsById(id)) {
            throw new ContractClauseNotFoundException("ID", id);
        }
        contractClauseJPARepository.deleteById(id);
    }
}
