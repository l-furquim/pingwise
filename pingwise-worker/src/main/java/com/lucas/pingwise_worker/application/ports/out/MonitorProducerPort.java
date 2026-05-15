package com.lucas.pingwise_worker.application.ports.out;

import com.lucas.pingwise_worker.domain.model.MonitorUpdate;

public interface MonitorProducerPort {

    void sendMonitorUpdate(MonitorUpdate monitorUpdate);

}
