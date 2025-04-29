package com.publicworks.public_works_management.contracts.application.mapper;

import com.publicworks.public_works_management.contracts.application.dto.response.ContractResponse;
import com.publicworks.public_works_management.contracts.application.service.CreateContractCommand;
import com.publicworks.public_works_management.contracts.domain.Contract;
import com.publicworks.public_works_management.contracts.domain.ContractClause;
import com.publicworks.public_works_management.contracts.domain.valueObjects.ContractId;
import com.publicworks.public_works_management.contracts.domain.valueObjects.ContractStatus;
import com.publicworks.public_works_management.contracts.infrastructure.ports.output.persistence.ContractModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ContractMappers {

    Contract createCommandToEntity(CreateContractCommand contractRequestDTO);

    // Remove When relationships exists
    @Mapping(target = "id", source = "contract.id.value")
    @Mapping(target = "bid", ignore = true)
    @Mapping(target = "contractor", ignore = true)
    ContractModel domainToModel(Contract contract);

    @Mapping(target = "id", source = "id", qualifiedByName = "longToContractId")
    Contract modelToDomain(ContractModel contractModel);

    @Mapping(target = "id", source = "contract.id.value")
    ContractResponse domainToResponseDTO(Contract contract);

    default ContractId map(Long id) {
        return id != null ? ContractId.from(String.valueOf(id)) : null;
    }

    @Named("longToContractId")
    default ContractId longToContractId(Long id) {
        if (id == null) {
            return null;
        }
        return ContractId.from(String.valueOf(id));
    }

}