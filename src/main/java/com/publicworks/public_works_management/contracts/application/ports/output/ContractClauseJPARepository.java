package com.publicworks.public_works_management.contracts.application.ports.output;

import com.publicworks.public_works_management.contracts.infrastructure.ports.output.persistence.ContractModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ContractJPARepository extends JpaRepository<ContractModel, Long>, JpaSpecificationExecutor<ContractModel> {
}
