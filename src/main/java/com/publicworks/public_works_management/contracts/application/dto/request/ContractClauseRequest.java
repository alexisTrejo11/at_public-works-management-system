package com.publicworks.public_works_management.contracts.application.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContractClauseRequest {
    @JsonProperty("contract_id")
    @NotNull(message = "contract_id is required")
    @NotBlank(message =  "contract_id can't be blank")
    String contractId;

    @JsonProperty("clause_number")
    @NotNull(message = "clause_number is required")
    @NotBlank(message =  "clause_number can't be blank")
    @Size(min = 3, max = 50, message = "clause_number must be between 3 and 50 characters")
    String clauseNumber;

    @JsonProperty("title")
    @NotNull(message = "title is required")
    @Size(min = 3, max = 200, message = "title must be between 3 and 50 characters")
    String title;

    @JsonProperty("content")
    String content;

    @NotNull(message = "mandatory is required")
    boolean mandatory = true;
}
