package com.publicworks.public_works_management.contracts.infrastructure.repository;

import com.publicworks.public_works_management.contracts.infrastructure.persistence.ContractEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractJPARepository extends JpaRepository<ContractEntity, Long> {
}
