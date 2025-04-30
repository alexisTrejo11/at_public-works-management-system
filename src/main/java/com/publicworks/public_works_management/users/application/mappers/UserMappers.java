package com.publicworks.public_works_management.users.application.mappers;


import com.publicworks.public_works_management.users.application.command.CreateUserCommand;
import com.publicworks.public_works_management.users.application.dto.UserResponse;
import com.publicworks.public_works_management.users.domain.entity.User;
import com.publicworks.public_works_management.users.infrastructure.persistence.models.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMappers {
    // Handle ID Map
    @Mapping(target = "password", ignore = true)
    User commandToDomain(CreateUserCommand userCommand);

    UserModel domainToModel(User user);

    UserResponse domainToResponse (User user);
}
