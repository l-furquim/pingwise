package com.lucas.pingwise.infrastructure.persistence.mapper;


import com.lucas.pingwise.domain.model.Notification;
import com.lucas.pingwise.infrastructure.persistence.entities.NotificationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface NotificationPersistenceMapper {

    NotificationEntity toEntity(Notification notification);
    Notification toDomain(NotificationEntity notificationEntity);

}
