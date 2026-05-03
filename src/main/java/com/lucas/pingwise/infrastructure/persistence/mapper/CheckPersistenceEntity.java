package com.lucas.pingwise.infrastructure.persistence.mapper;

import com.lucas.pingwise.domain.model.Check;
import com.lucas.pingwise.infrastructure.persistence.entities.CheckEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CheckPersistenceEntity {

    CheckEntity toEntity(Check check);
    Check toDomain(CheckEntity checkEntity);

}
