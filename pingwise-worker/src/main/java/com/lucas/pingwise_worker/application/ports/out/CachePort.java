package com.lucas.pingwise_worker.application.ports.out;

import java.util.concurrent.TimeUnit;

public interface CachePort {

    Long increment(String key);
    void expire(String key, Long expire, TimeUnit timeUnit);
    Long delete(String key);

}
