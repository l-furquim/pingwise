package com.lucas.pingwise.application.mappers;

import com.lucas.pingwise.adapters.in.rest.invite.dto.InviteResponse;
import com.lucas.pingwise.domain.model.Invite;
import org.springframework.stereotype.Component;

@Component
public class InviteApplicationMapper {

    public InviteResponse toResponse(Invite invite) {
        return new InviteResponse(
                invite.getEmail(),
                invite.getRole().getValue(),
                invite.getStatus().getValue(),
                invite.getExpiresAt(),
                invite.getCreatedAt()
        );
    }

}
