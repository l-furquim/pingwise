package com.lucas.pingwise_worker.application.ports.out;

import com.lucas.pingwise_worker.domain.model.Check;

public interface CheckRepository {

    void save(Check check);

}
