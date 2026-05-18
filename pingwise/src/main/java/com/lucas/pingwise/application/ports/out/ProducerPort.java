package com.lucas.pingwise.application.ports.out;

import com.lucas.pingwise.domain.model.CheckJob;

import java.util.List;

public interface ProducerPort {

    void sendCheckMessageProducer(CheckJob checkJob);
    void sendCheckMessageProducerBatch(List<CheckJob> checkJobs);
}
