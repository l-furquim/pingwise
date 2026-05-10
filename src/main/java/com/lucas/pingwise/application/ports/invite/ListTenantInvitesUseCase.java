package com.lucas.pingwise.application.ports.invite;

import com.lucas.pingwise.adapters.in.rest.invite.dto.InviteResponse;

import java.util.List;

public interface ListTenantInvitesUseCase {

    List<InviteResponse> execute();

}
