package com.lucas.pingwise.infrastructure.persistence.mapper;

import com.lucas.pingwise.domain.model.User;
import com.lucas.pingwise.infrastructure.persistence.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserPersistenceMapper {

    UserEntity toEntity(User user);
    User toDomain(UserEntity userEntity);

}
