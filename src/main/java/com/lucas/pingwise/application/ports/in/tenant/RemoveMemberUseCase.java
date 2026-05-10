package com.lucas.pingwise.application.ports.in.tenant;

import java.util.UUID;

public interface RemoveMemberUseCase {

    void execute(UUID memberId);

}
