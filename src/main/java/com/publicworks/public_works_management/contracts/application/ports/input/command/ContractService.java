package com.publicworks.public_works_management.contracts.application.ports.input.command;

import com.publicworks.public_works_management.contracts.application.dto.request.ContractSearchCriteria;
import com.publicworks.public_works_management.contracts.application.dto.response.ContractSummary;
import com.publicworks.public_works_management.contracts.domain.Contract;
import com.publicworks.public_works_management.contracts.domain.valueObjects.ContractId;
import com.publicworks.public_works_management.shared.page.PageInput;
import org.springframework.data.domain.Page;

public interface ContractService {
    Contract getContractById(ContractId contractId);
    Page<Contract> searchContracts(ContractSearchCriteria criteria, PageInput page);
    Contract createContract(Contract contract);
    Contract updateContract(Contract contract);
    void deleteContract(ContractId contractId);
    ContractSummary generateReport(Contract contract);
}