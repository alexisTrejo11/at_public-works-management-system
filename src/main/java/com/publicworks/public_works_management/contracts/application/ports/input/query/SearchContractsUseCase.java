package com.publicworks.public_works_management.contracts.application.ports.input.query;

import com.publicworks.public_works_management.contracts.application.dto.request.ContractSearchCriteria;
import com.publicworks.public_works_management.contracts.application.dto.response.ContractSummary;
import com.publicworks.public_works_management.shared.page.PageInput;
import org.springframework.data.domain.Page;

public interface SearchContractsUseCase {
    Page<ContractSummary> execute(ContractSearchCriteria criteria, PageInput page);
}