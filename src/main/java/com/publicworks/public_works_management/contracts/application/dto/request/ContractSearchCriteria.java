package com.publicworks.public_works_management.contracts.application.dto.request;

import com.publicworks.public_works_management.contracts.infrastructure.ports.output.persistence.ContractModel;
import jakarta.persistence.criteria.Predicate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    public Specification<ContractModel> generateSpecification() {
        return  (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (this.getContractNumber() != null && !this.getContractNumber().isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("contractNumber"), "%" + this.getContractNumber() + "%"));
            }

            if (this.getTitle() != null && !this.getTitle().isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("title"), "%" + this.getTitle() + "%"));
            }

            if (this.getStatus() != null && !this.getStatus().isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("status"), this.getStatus()));
            }

            if (this.getStartDateFrom() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("startDate"), this.getStartDateFrom()));
            }

            if (this.getStartDateTo() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("startDate"), this.getStartDateTo()));
            }

            if (this.getEndDateFrom() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("endDate"), this.getEndDateFrom()));
            }

            if (this.getEndDateTo() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("endDate"), this.getEndDateTo()));
            }

            if (this.getMinAmount() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("amount"), this.getMinAmount()));
            }

            if (this.getMaxAmount() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("amount"), this.getMaxAmount()));
            }

            if (this.getActive() != null) {
                if (this.getActive()) {
                    predicates.add(criteriaBuilder.and(
                            criteriaBuilder.lessThan(root.get("endDate"), LocalDate.now()),
                            criteriaBuilder.notEqual(root.get("status"), "COMPLETED"),
                            criteriaBuilder.notEqual(root.get("status"), "TERMINATED")
                    ));
                } else {
                    predicates.add(criteriaBuilder.or(
                            criteriaBuilder.greaterThanOrEqualTo(root.get("endDate"), LocalDate.now()),
                            criteriaBuilder.equal(root.get("status"), "COMPLETED"),
                            criteriaBuilder.equal(root.get("status"), "TERMINATED")
                    ));
                }
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
