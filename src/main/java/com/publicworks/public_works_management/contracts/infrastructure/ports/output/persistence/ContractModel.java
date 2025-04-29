package com.publicworks.public_works_management.contracts.infrastructure.ports.output.persistence;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "contracts")
public class ContractModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, name = "contract_number")
    private String contractNumber;

    //@OneToOne
    //@JoinColumn(name = "bid_id")
    private Long bid;

    //@ManyToOne
    //@JoinColumn(name = "contractor_id")
    private Long contractor;

    @Column(nullable = false, name = "start_date")
    private LocalDate startDate;

    @Column(nullable = false, name = "end_date")
    private LocalDate endDate;

    @Column(nullable = false, name = "total_amount")
    private Double amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "status")
    private ContractStatus status;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "contract_id")
    private List<ContractClause> clauses = new ArrayList<>();
}