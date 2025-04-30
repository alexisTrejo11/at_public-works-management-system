package com.publicworks.public_works_management.bids.infrastructure.persistence;

import com.publicworks.public_works_management.bids.domain.valueobjects.BidStatus;
import com.publicworks.public_works_management.contracts.infrastructure.ports.output.persistence.ContractModel;
import com.publicworks.public_works_management.shared.models.SQLModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "bid")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BidModel extends SQLModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Column(name = "code")
    private String code;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "opening_date")
    private LocalDate openingDate;

    @Column(name = "closing_date")
    private LocalDate closingDate;

    @Column(name = "budget", nullable = false)
    private BigDecimal budget;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private BidStatus status;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "bid_id")
    private List<BidDocumentModel> documents = new ArrayList<>();

    @OneToOne(mappedBy = "bid", cascade = CascadeType.ALL)
    private ContractModel contract;
}