package com.lucas.pingwise.infrastructure.persistence.mapper;

import com.lucas.pingwise.domain.model.Incident;
import com.lucas.pingwise.infrastructure.persistence.entities.IncidentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface IncidentPersistenceMapper {

    IncidentEntity toEntity(Incident incident);
    Incident toDomain(IncidentEntity incidentEntity);

}
