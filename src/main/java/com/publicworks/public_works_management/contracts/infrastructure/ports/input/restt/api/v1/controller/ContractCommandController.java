package com.publicworks.public_works_management.contracts.infrastructure.ports.input.restt.api.v1.controller;

import com.publicworks.public_works_management.contracts.application.dto.request.ContractClauseRequest;
import com.publicworks.public_works_management.contracts.application.dto.request.CreateContractRequest;
import com.publicworks.public_works_management.contracts.application.dto.response.ContractResponse;
import com.publicworks.public_works_management.contracts.application.ports.input.command.AddContractClauseUseCase;
import com.publicworks.public_works_management.contracts.application.ports.input.command.ApproveContractUseCase;
import com.publicworks.public_works_management.contracts.application.ports.input.command.CreateContractUseCase;
import com.publicworks.public_works_management.contracts.application.ports.input.query.SoftDeleteContractUseCase;
import com.publicworks.public_works_management.contracts.domain.exceptions.ContractNotFoundException;
import com.publicworks.public_works_management.contracts.domain.exceptions.ContractValidationException;
import com.publicworks.public_works_management.contracts.domain.valueObjects.ContractId;
import com.publicworks.public_works_management.shared.response.http.ResponseWrapper;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/contracts")
public class ContractCommandController {

    private final CreateContractUseCase createContractUseCase;
    private final AddContractClauseUseCase addContractClauseUseCase;
    private final SoftDeleteContractUseCase deleteContractUseCase;
    private final ApproveContractUseCase approveContractUseCase;

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

    /**
     * Approves a Contract.
     *
     * @param contractId: the input data of the HTTP request.
     * @return None.
     * @throws ConstraintViolationException: if request data does not pass the validation obligatory fields or data type/format are wrong.
     * @throws ContractValidationException: if request data does not pass the business rules.
     * @throws ContractNotFoundException: if contract does not exist on database.
     */
    @PostMapping("{contractId}/approve")
    public ResponseWrapper<ContractResponse> approveContract(@PathVariable String contractId) {
        approveContractUseCase.execute(contractId);
        return ResponseWrapper.ok("Contract Clause Successfully Added");
    }

    /**
     * Add Contract Clause.
     *
     * @param clauseRequest: the input data of the HTTP request.
     * @return None.
     * @throws ConstraintViolationException: if request data does not pass the validation obligatory fields or data type/format are invalid.
     * @throws ContractValidationException: if request data does not pass the business rules.
     * @throws ContractNotFoundException: if contract does not exist on database.
     */
    @PostMapping("/clause")
    public ResponseWrapper<ContractResponse> addContractClause(@RequestBody @Valid ContractClauseRequest clauseRequest) {
        addContractClauseUseCase.execute(clauseRequest);
        return ResponseWrapper.ok("Contract Clause Successfully Added");
    }

    // TODO: Add Update Scenarios Endpoints


    /**
     * Soft Delete Contract Clause.
     *
     * @param contractId: the input data of the HTTP request.
     * @return None.
     * @throws ContractNotFoundException: if contract does not exist on database.
     */
    @DeleteMapping("/{contractId}")
    public ResponseWrapper<ContractResponse> deleteContract(@RequestParam @Valid String contractId) {
        deleteContractUseCase.execute(ContractId.from(contractId));
        return ResponseWrapper.deleted("Contract");
    }
}
