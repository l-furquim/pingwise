package com.lucas.pingwise.infrastructure.persistence.mapper;

import com.lucas.pingwise.domain.model.Monitor;
import com.lucas.pingwise.infrastructure.persistence.entities.MonitorEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MonitorPersistenceMapper {

    MonitorEntity toEntity(Monitor monitor);
    Monitor toDomain(MonitorEntity monitorEntity);

}
