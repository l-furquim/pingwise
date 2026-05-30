package com.lucas.pingwise.infrastructure.cache;

import com.lucas.pingwise.application.ports.out.CachePort;
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
    public Long get(String key) {
        return this.redisTemplate.opsForValue().get(key);
    }
}
