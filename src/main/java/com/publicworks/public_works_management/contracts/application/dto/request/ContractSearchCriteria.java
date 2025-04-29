package com.publicworks.public_works_management.contracts.application.dto.request;


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
public class ContractSearchCriteria {
    private String contractNumber;
    private String title;
    private String status;
    private LocalDate startDateFrom;
    private LocalDate startDateTo;
    private LocalDate endDateFrom;
    private LocalDate endDateTo;
    private BigDecimal minAmount;
    private BigDecimal maxAmount;
    private Boolean active;
}
