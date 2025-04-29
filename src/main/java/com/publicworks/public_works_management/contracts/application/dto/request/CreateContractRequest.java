package com.publicworks.public_works_management.contracts.application.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.publicworks.public_works_management.contracts.application.service.CreateContractCommand;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateContractRequest {
    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("start_date")
    private LocalDate startDate;

    @JsonProperty("end_date")
    private LocalDate endDate;

    @JsonProperty("budget")
    private BigDecimal budget;

    public CreateContractCommand toCommand() {
        return new CreateContractCommand(
                name,
                description,
                startDate,
                endDate,
                budget,
                null
        );
    }
}
