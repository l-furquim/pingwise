package com.lucas.pingwise_worker.infrastructure.persistence.mapper;

import com.lucas.pingwise_worker.domain.model.Check;
import com.lucas.pingwise_worker.infrastructure.persistence.entities.CheckEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CheckPersistenceMapper {

    CheckEntity toEntity(Check check);
    Check toDomain(CheckEntity checkEntity);

}
