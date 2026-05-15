package com.lucas.pingwise_worker.infrastructure.persistence.mapper;

import com.lucas.pingwise_worker.domain.model.Incident;
import com.lucas.pingwise_worker.infrastructure.persistence.entities.IncidentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface IncidentPersistenceMapper {

    IncidentEntity toEntity(Incident incident);
    Incident toDomain(IncidentEntity incidentEntity);

}
