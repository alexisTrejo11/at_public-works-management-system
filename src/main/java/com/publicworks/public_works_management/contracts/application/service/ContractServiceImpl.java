package com.publicworks.public_works_management.contracts.application.service;

import com.publicworks.public_works_management.contracts.application.dto.request.ContractSearchCriteria;
import com.publicworks.public_works_management.contracts.application.dto.response.ContractSummary;
import com.publicworks.public_works_management.contracts.application.mapper.ContractMappers;
import com.publicworks.public_works_management.contracts.application.ports.input.command.ContractService;
import com.publicworks.public_works_management.contracts.application.ports.output.ContractClauseJPARepository;
import com.publicworks.public_works_management.contracts.application.ports.output.ContractJPARepository;
import com.publicworks.public_works_management.contracts.domain.Contract;
import com.publicworks.public_works_management.contracts.domain.exceptions.ContractNotFoundException;
import com.publicworks.public_works_management.contracts.domain.valueObjects.ContractId;
import com.publicworks.public_works_management.contracts.infrastructure.ports.output.persistence.ContractModel;
import com.publicworks.public_works_management.shared.page.PageInput;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ContractServiceImpl implements ContractService {
    private static final String CONTRACT_CACHE = "contracts";

    private final ContractJPARepository contractJPARepository;
    private final ContractMappers contractMappers;

    @Override
    @Cacheable(value = CONTRACT_CACHE, key = "#contractId")
    public Contract getContractById(ContractId contractId) {
        Optional<ContractModel> optionalContractModel = contractJPARepository.findById(contractId.toLong());
        return optionalContractModel
                .map(contractMappers::modelToDomain)
                .orElseThrow(() -> new ContractNotFoundException("ID", contractId));
    }

    @Override
    public Page<Contract> searchContracts(ContractSearchCriteria criteria, PageInput page) {
        var specification = criteria.generateSpecification();
        Pageable pageable = PageRequest.of(page.pageNumber(), page.pageSize());

        Page<ContractModel> contractModelsPage = contractJPARepository.findAll(specification, pageable);

        return contractModelsPage.map(contractMappers::modelToDomain);
    }

    @Override
    @Transactional
    public Contract createContract(Contract contract) {
        ContractModel contractModel = contractMappers.domainToModel(contract);
        ContractModel savedContractModel = contractJPARepository.save(contractModel);
        return contractMappers.modelToDomain(savedContractModel);
    }

    @Override
    @Transactional
    @CachePut(value = CONTRACT_CACHE, key = "#contract.id.toLong()")
    public Contract updateContract(Contract contract) {
        Optional<ContractModel> optionalContractModel = contractJPARepository.findById(contract.getId().toLong());
        if (optionalContractModel.isEmpty()) {
            throw new ContractNotFoundException("ID", contract.getId().toLong());
        }
        ContractModel existingContractModel = optionalContractModel.get();
        //contractMappers.updateModelFromDomain(contract, existingContractModel); // TO BE IMPLEMENTED
        ContractModel updatedContractModel = contractJPARepository.save(existingContractModel);
        return contractMappers.modelToDomain(updatedContractModel);
    }

    @Override
    @Transactional
    @CacheEvict(value = CONTRACT_CACHE, key = "#contractId")
    public void deleteContract(ContractId contractId) {
        if (!contractJPARepository.existsById(contractId.toLong())) {
            throw new ContractNotFoundException("ID", contractId);
        }
        contractJPARepository.deleteById((contractId.toLong()));
    }

    @Override
    public ContractSummary generateReport(Contract contract) {
        LocalDate now = LocalDate.now();
        BigDecimal completionPercentage = BigDecimal.ZERO; // PLACE HOLDER
        if (contract.getAmount() != null && contract.getAmount().compareTo(BigDecimal.ZERO) > 0)
        {
            completionPercentage = BigDecimal.ZERO; // PLACE HOLDER
        }

        return ContractSummary.builder()
                .id(contract.getId().value())
                .contractNumber(contract.getContractNumber())
                .contractorName("John Doe") // Dummy value
                .contractType("Public Work") // Dummy value
                .description(contract.getDescription())
                .amount(contract.getAmount())
                .projectName(contract.getTitle())
                .currency("MX") // Dummy value
                .clausesCount(contract.getClauses().size())
                .isActive(contract.is_active())
                .startDate(contract.getStartDate())
                .endDate(contract.getEndDate())
                .status(contract.getStatus() != null ? contract.getStatus().toString() : "Unknown")
                .daysRemaining((int) contract.getDaysRemaining())
                .completionPercentage(completionPercentage)
                .createdAt(now) //Dummy Value
                .build();
    }
}