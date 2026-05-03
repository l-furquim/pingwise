package com.lucas.pingwise.infrastructure.persistence.mapper;

import com.lucas.pingwise.domain.model.Plan;
import com.lucas.pingwise.infrastructure.persistence.entities.PlanEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PlanPersistenceMapper {

    PlanEntity toEntity(Plan plan);
    Plan toDomain(PlanEntity planEntity);

}
