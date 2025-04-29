package com.publicworks.public_works_management.contracts.application.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContractSummary {
    @JsonProperty("id")
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

    @JsonProperty("status")
    private String status;

    @JsonProperty("clauses_count")
    private long clausesCount;

    @JsonProperty("completion_percentage")
    private BigDecimal completionPercentage;

    @JsonProperty("days_remaining")
    private int daysRemaining;

    @JsonProperty("is_active")
    private boolean isActive;

    @JsonProperty("contractor_name")
    private String contractorName;

    @JsonProperty("created_at")
    private LocalDate createdAt;

    @JsonProperty("project_name")
    private String projectName;

    @JsonProperty("contract_type")
    private String contractType;

    @JsonProperty("currency")
    private String currency;

}