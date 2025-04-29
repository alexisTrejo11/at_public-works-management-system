package com.publicworks.public_works_management.contracts.application.usecase.query;

import com.publicworks.public_works_management.contracts.application.dto.request.ContractSearchCriteria;
import com.publicworks.public_works_management.contracts.application.dto.response.ContractSummary;
import com.publicworks.public_works_management.contracts.application.mapper.ContractMappers;
import com.publicworks.public_works_management.contracts.application.ports.input.command.ContractService;
import com.publicworks.public_works_management.contracts.application.ports.input.query.SearchContractsUseCase;
import com.publicworks.public_works_management.contracts.domain.Contract;
import com.publicworks.public_works_management.shared.page.PageInput;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SearchContractsUseCaseImpl implements SearchContractsUseCase {
    private ContractService contractService;
    private ContractMappers contractMappers;

    @Override
    public Page<ContractSummary> execute(ContractSearchCriteria criteria, PageInput page) {
       Page<Contract> contractPage  = contractService.searchContracts(criteria, page);

       return contractPage.map(contract -> contractService.generateReport(contract));
    }
}
