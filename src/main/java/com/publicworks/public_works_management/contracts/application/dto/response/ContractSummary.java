package com.publicworks.public_works_management.contracts.application.dto.response;

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
public class ContractSummaryResponse {
    private String id;
    private String contractNumber;
    private String title;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal amount;
    private String status;
    private int clausesCount;
    private BigDecimal completionPercentage;
    private long daysRemaining;
    private boolean isActive;
}