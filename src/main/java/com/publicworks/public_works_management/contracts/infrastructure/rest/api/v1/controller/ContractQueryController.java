package com.publicworks.public_works_management.contracts.infrastructure.rest.api.v1.controller;


import com.publicworks.public_works_management.contracts.application.dto.response.ContractResponse;
import com.publicworks.public_works_management.shared.response.http.ResponseWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/contracts")
public class ContractQueryController {

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
