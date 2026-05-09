package com.lucas.pingwise.application.ports.in.monitor;

import com.lucas.pingwise.adapters.in.rest.monitor.dto.MonitorResponse;
import com.lucas.pingwise.application.ports.in.monitor.dto.CreateMonitorCommand;

public interface CreateMonitorUseCase {

    MonitorResponse execute(CreateMonitorCommand command);

}
