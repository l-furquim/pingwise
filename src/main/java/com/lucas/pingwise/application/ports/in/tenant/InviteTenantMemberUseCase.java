package com.lucas.pingwise.application.ports.in.tenant;

import com.lucas.pingwise.application.ports.in.tenant.dto.InviteTenantMemberCommand;

public interface InviteTenantMemberUseCase {
    void execute(InviteTenantMemberCommand command);
}
