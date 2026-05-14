package com.lucas.pingwise.infrastructure.scheduler;

import com.lucas.pingwise.application.ports.in.check.DispatchDueChecksUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MonitorDispatchScheduler {

    private final DispatchDueChecksUseCase dispatchDueChecksUseCase;

    @Scheduled(fixedDelay = 30_000)
    public void dispatch() {
        log.info("Dispatching due monitors...");
        dispatchDueChecksUseCase.execute();
    }

}
