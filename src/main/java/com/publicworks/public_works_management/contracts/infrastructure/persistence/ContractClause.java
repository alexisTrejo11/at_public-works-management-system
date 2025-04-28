package com.publicworks.public_works_management.contracts.infrastructure.persistence;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "contract_clauses")
@NoArgsConstructor
@Data
public class ContractClause {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String clauseNumber;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(name = "is_mandatory", nullable = false)
    private boolean mandatory;

    @Column(name = "sort_order", nullable = false)
    private int sortOrder;

    // No reference back to Contract to maintain unidirectional relationship
}