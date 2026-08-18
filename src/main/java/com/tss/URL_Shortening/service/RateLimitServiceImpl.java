package com.tss.URL_Shortening.service;

import com.tss.URL_Shortening.exception.GlobalExceptionHandler;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.data.redis.RedisSystemException;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@RequiredArgsConstructor
@Service
public class RateLimitServiceImpl implements RateLimitService{

    private final StringRedisTemplate redisTemplate;
    private static final Logger log= LoggerFactory.getLogger(RateLimitServiceImpl.class);

    @Override
    public boolean isAllowed(String key, int maxRequests, int windowSeconds) {

        try {
            Long count = redisTemplate.opsForValue().increment(key);

            if (count == null) {
                log.error("Redis returned null count for rate-limit key: {}", key);
                // Fail-open
                return true;
            }

            if (count == 1) {
                redisTemplate.expire(key, Duration.ofSeconds(windowSeconds));
            }
            return count <= maxRequests;

        } catch (RedisConnectionFailureException e) {
            log.error("Redis connection failed while applying rate limit. key={}", key, e);
            return true;
        } catch (RedisSystemException e) {
            log.error("Redis system error while applying rate limit. key={}", key, e);
            return true;
        }
    }
}
