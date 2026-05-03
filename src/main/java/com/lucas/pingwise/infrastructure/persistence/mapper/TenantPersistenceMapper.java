package com.lucas.pingwise.infrastructure.persistence.mapper;


import com.lucas.pingwise.domain.model.Tenant;
import com.lucas.pingwise.infrastructure.persistence.entities.TenantEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TenantPersistenceMapper {

    TenantEntity toEntity(Tenant tenant);
    Tenant toDomain(TenantEntity tenantEntity);

}
