package com.lucas.pingwise.application.ports.in.monitor;

import com.lucas.pingwise.application.ports.in.monitor.dto.UpdateMonitorStatusCommand;

public interface UpdateMonitorStatusUseCase {

    void execute(UpdateMonitorStatusCommand command);

}
