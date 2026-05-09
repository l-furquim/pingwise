package com.lucas.pingwise.application.ports.in.tenant;

import com.lucas.pingwise.adapters.in.rest.tenant.dto.TenantMemberResponse;

import java.util.List;

public interface GetTenantMembersUseCase {

    List<TenantMemberResponse> execute();

}
