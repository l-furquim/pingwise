package com.lucas.pingwise_worker.application.ports.in;

import com.lucas.pingwise_worker.application.ports.in.dto.HandleCheckJobCommand;

public interface HandleCheckJobUseCase {

    void execute(HandleCheckJobCommand command);

}
