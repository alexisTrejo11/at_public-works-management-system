package com.publicworks.public_works_management.contracts.application.mapper;

import com.publicworks.public_works_management.contracts.application.dto.request.ContractClauseRequest;
import com.publicworks.public_works_management.contracts.domain.ContractClause;
import com.publicworks.public_works_management.contracts.infrastructure.ports.output.persistence.ContractClauseModel;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ContractClauseMappers {
    ContractClause requestDTOToDomain(ContractClauseRequest clauseRequest);
    ContractClause modelToDomain(ContractClauseModel model);
    ContractClauseModel domainToModel(ContractClause domain);
    void updateModelFromDomain(ContractClause domain, @MappingTarget ContractClauseModel model);
}
