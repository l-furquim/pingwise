package com.lucas.pingwise.infrastructure.persistence.mapper;

import com.lucas.pingwise.domain.model.Invite;
import com.lucas.pingwise.infrastructure.persistence.entities.InviteEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface InvitePersistenceMapper {

    Invite toDomain(InviteEntity inviteEntity);
    InviteEntity toEntity(Invite invite);

}
