package com.publicworks.public_works_management.contracts.infrastructure.rest.api.v1.controller;


import com.publicworks.public_works_management.contracts.application.dto.request.CreateContractRequest;
import com.publicworks.public_works_management.contracts.application.dto.response.ContractResponse;
import com.publicworks.public_works_management.contracts.application.usecase.CreateContractUseCase;
import com.publicworks.public_works_management.contracts.domain.exceptions.ContractValidationException;
import com.publicworks.public_works_management.shared.response.http.ResponseWrapper;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/contracts")
public class ContractQueryController {

    private final CreateContractUseCase createContractUseCase;

    /**
     * Create Contract.
     *
     * @param contractRequest: the input data of the HTTP request.
     * @return contractCreated: the DTO of the Contract.
     * @throws ConstraintViolationException: if request data does not pass the validation obligatory fields or data type/format are wrong.
     * @throws ContractValidationException: if request data does not pass the business rules.
     */
    @PostMapping
    public ResponseWrapper<ContractResponse> createContract(@RequestBody @Valid CreateContractRequest contractRequest) {
        ContractResponse contractCreated = createContractUseCase.execute(contractRequest.toCommand());
        return ResponseWrapper.created(contractCreated, "Contract");
    }


    @GetMapping("/{contractId}")
    private ResponseWrapper<ContractResponse> getContractById(@PathVariable String contractId) {
        ContractResponse contractResponse = new ContractResponse(
                contractId,
                "Contract Name",
                "Contract Description",
                "Contract Type",
                LocalDate.now(),
                LocalDate.now(),
                BigDecimal.TEN
        );

        return ResponseWrapper.found(contractResponse, "Contract");
    }
}
