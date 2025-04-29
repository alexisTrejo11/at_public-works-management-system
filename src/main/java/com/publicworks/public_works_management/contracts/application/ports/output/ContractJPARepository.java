package com.publicworks.public_works_management.contracts.application.ports.output;

import com.publicworks.public_works_management.contracts.domain.Contract;
import com.publicworks.public_works_management.contracts.domain.ContractClause;
import com.publicworks.public_works_management.contracts.domain.valueObjects.ContractId;
import com.publicworks.public_works_management.contracts.infrastructure.ports.output.persistence.ContractModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ContractJPARepository extends JpaRepository<ContractModel, Long>, JpaSpecificationExecutor<ContractModel> {
}
