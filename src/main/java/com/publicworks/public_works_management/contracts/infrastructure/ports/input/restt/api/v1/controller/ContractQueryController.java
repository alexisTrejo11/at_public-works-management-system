package com.publicworks.public_works_management.contracts.infrastructure.ports.input.restt.api.v1.controller;

import com.publicworks.public_works_management.contracts.application.dto.request.ContractSearchCriteria;
import com.publicworks.public_works_management.contracts.application.dto.response.ContractResponse;
import com.publicworks.public_works_management.contracts.application.dto.response.ContractSummary;
import com.publicworks.public_works_management.contracts.application.ports.input.query.GetContractDetailsUseCase;
import com.publicworks.public_works_management.contracts.application.ports.input.query.SearchContractsUseCase;
import com.publicworks.public_works_management.contracts.domain.exceptions.ContractNotFoundException;
import com.publicworks.public_works_management.contracts.domain.valueObjects.ContractId;
import com.publicworks.public_works_management.shared.page.PageInput;
import com.publicworks.public_works_management.shared.response.http.ResponseWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/contracts")
public class ContractQueryController {
    private final GetContractDetailsUseCase getContractUseCase;
    private final SearchContractsUseCase searchContractsUseCase;

    /**
     * Get Contract by ID.
     *
     * @param contractId: the unique identifier of the Contract.
     * @return ContractResponse: the output response DTO of the Contract.
     * @throws ContractNotFoundException: if requested Contract does not exist.
     */
    @GetMapping("/{contractId}")
    private ResponseWrapper<ContractResponse> getContractById(@PathVariable String contractId) {
        ContractResponse contractResponse = getContractUseCase.execute(ContractId.from(contractId));

        return ResponseWrapper.found(contractResponse, "Contract");
    }

    @GetMapping
    private ResponseWrapper<Page<ContractSummary>> searchContracts(
            @ModelAttribute PageInput pageInput,
            @ModelAttribute ContractSearchCriteria searchCriteria) {

        Page<ContractSummary> contractSummaryPage = searchContractsUseCase.execute(searchCriteria, pageInput);

        return ResponseWrapper.found(contractSummaryPage, "Contract");
    }

}
