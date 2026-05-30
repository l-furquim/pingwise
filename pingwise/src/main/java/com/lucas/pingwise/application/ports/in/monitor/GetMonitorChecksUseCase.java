package com.lucas.pingwise.application.ports.in.monitor;

import com.lucas.pingwise.adapters.in.rest.tenant.dto.CheckResponse;
import com.lucas.pingwise.application.ports.in.monitor.dto.GetMonitorChecksCommand;

import java.util.List;

public interface GetMonitorChecksUseCase {

    List<CheckResponse> execute(GetMonitorChecksCommand command);

}
