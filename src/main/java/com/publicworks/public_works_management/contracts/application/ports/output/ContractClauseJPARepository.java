package com.publicworks.public_works_management.contracts.application.ports.output;

import com.publicworks.public_works_management.contracts.infrastructure.ports.output.persistence.ContractClauseModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ContractClauseJPARepository extends JpaRepository<ContractClauseModel, Long>, JpaSpecificationExecutor<ContractClauseModel> {

}
