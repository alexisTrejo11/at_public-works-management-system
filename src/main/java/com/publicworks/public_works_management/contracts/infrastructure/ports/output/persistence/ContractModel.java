package com.publicworks.public_works_management.contracts.infrastructure.ports.output.persistence;

import com.publicworks.public_works_management.bids.infrastructure.persistence.BidModel;
import com.publicworks.public_works_management.contracts.domain.valueObjects.ContractStatus;
import com.publicworks.public_works_management.shared.models.SQLModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "contracts")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ContractModel extends SQLModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, name = "contract_number")
    private String contractNumber;

    @OneToOne
    @JoinColumn(name = "bid_id")
    private BidModel bid;

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
    private List<ContractClauseModel> clauses = new ArrayList<>();
}