package com.lucas.pingwise.application.ports.out;

import java.util.concurrent.TimeUnit;

public interface CachePort {

    Long increment(String key);
    void expire(String key, Long expire, TimeUnit timeUnit);
    Long get(String key);

}
