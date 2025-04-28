package com.publicworks.public_works_management.contracts.application.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.publicworks.public_works_management.contracts.domain.valueObjects.ContractId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContractResponse {
    @JsonProperty("contract_id")
    private String id;

    @JsonProperty("contract_number")
    private String contractNumber;

    @JsonProperty("title")
    private String title;

    @JsonProperty("description")
    private String description;

    @JsonProperty("start_date")
    private LocalDate startDate;

    @JsonProperty("end_date")
    private LocalDate endDate;

    @JsonProperty("amount")
    private BigDecimal amount;
}
