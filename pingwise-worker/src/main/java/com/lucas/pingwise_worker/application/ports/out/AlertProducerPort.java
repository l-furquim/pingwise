package com.lucas.pingwise_worker.application.ports.out;

import com.lucas.pingwise_worker.domain.model.Alert;

public interface AlertProducerPort {

    void sendAlert(Alert alert);

}
