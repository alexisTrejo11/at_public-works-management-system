package com.publicworks.public_works_management.contracts.infrastructure.ports.output.persistence;

import com.publicworks.public_works_management.shared.models.SQLModel;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "contract_clauses")
@NoArgsConstructor
@Data
public class ContractClauseModel extends SQLModel {
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
}