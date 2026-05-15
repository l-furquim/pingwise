package com.lucas.pingwise_worker.infrastructure.cache;

import com.lucas.pingwise_worker.application.ports.out.CachePort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Component
public class RedisCacheAdapterImpl implements CachePort {

    private final RedisTemplate<String, Long> redisTemplate;


    @Override
    public Long increment(String key) {
        return this.redisTemplate.opsForValue().increment(key);
    }

    @Override
    public void expire(String key, Long expire, TimeUnit timeUnit) {
        this.redisTemplate.expire(key, expire, timeUnit);
    }

    @Override
    public Long delete(String key) {
        final var previousCount = this.redisTemplate.opsForValue().get(key);
        this.redisTemplate.delete(key);

        return previousCount;
    }
}
