package com.tss.URL_Shortening.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@RequiredArgsConstructor
@Service
public class RateLimitServiceImpl implements RateLimitService{
    private final StringRedisTemplate redisTemplate;
    @Override
    public boolean isAllowed(String key, int maxRequests, int windowSeconds) {

        Long count = redisTemplate.opsForValue().increment(key);

        if (count != null && count == 1) {
            redisTemplate.expire(key, Duration.ofSeconds(windowSeconds));
        }

        return count != null && count <= maxRequests;
    }
}
