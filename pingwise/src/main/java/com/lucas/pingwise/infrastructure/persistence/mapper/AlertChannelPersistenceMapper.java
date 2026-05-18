package com.lucas.pingwise.infrastructure.persistence.mapper;


import com.lucas.pingwise.domain.model.AlertChannel;
import com.lucas.pingwise.infrastructure.persistence.entities.AlertChannelEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AlertChannelPersistenceMapper {

    AlertChannelEntity toEntity(AlertChannel alertChannel);
    AlertChannel toDomain(AlertChannelEntity alertChannelEntity);

}
