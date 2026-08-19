package com.tss.URL_Shortening.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.data.redis.RedisSystemException;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class TokenBlacklistRedisService {

    private static final String BLACKLIST_PREFIX = "jwt:blacklist:";
    private final StringRedisTemplate redisTemplate;
    private static final Logger log= LoggerFactory.getLogger(TokenBlacklistRedisService.class);

    public void blacklistToken(String token, Duration expiration) {

        if (expiration.isZero() || expiration.isNegative()) {
            return;
        }

        String key = BLACKLIST_PREFIX + token;

        try {
            redisTemplate.opsForValue().set(key, "1", expiration);

        } catch (RedisConnectionFailureException e) {

            log.error("Redis connection failed while blacklisting JWT", e);
        } catch (RedisSystemException e) {
            log.error("Redis system error while blacklisting JWT", e);
        }
    }

    public boolean isBlacklisted(String token) {

        String key = BLACKLIST_PREFIX + token;

        try {
            return Boolean.TRUE.equals(redisTemplate.hasKey(key));

        } catch (RedisConnectionFailureException e) {
            log.error("Redis connection failed while checking JWT blacklist", e);
            return false;

        } catch (RedisSystemException e) {
            log.error("Redis system error while checking JWT blacklist", e);
            return false;
        }
    }
}

